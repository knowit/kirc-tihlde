"use strict";
const AWS = require("aws-sdk");
const sqs = new AWS.SQS({ apiVersion: "2012-11-05" });
const dynamoDB = new AWS.DynamoDB();

AWS.config.update({ region: "eu-central-1" });

module.exports.send = async (event) => {
  console.log("event", event);
  const message = JSON.stringify(event.body);
  console.log("message", message);
  // TODO:  validate event

  const params = {
    MessageBody: message,
    QueueUrl: process.env.QUEUE_URL,
    MessageGroupId: "Message"
  }
  console.log("params", params)
  var statusCode, result;
  try {
    await sqs
      .sendMessage(params)
      .promise();
    statusCode = 201;
    result = "Message accepted";
  } catch (error) {
    console.log("error", error);
    statusCode = 500;
    result = error;
  }
  return {
    statusCode: statusCode,
    body: JSON.stringify({
      message: result,
    }),
  };
};

module.exports.get = async (event) => {
  console.log("event", event);
  const message = JSON.stringify(event.body);
  console.log("message", message);
  // TODO:  validate event

  var params = {TableName: "kirc"}
  var statusCode, result;
  dynamoDB
      .scan(params, (err, data) => {
        if (err) {
          statusCode = 500
          result = err;
        }
        else {
          statusCode = 201
          result = data;
        }  
      })

  return {
    statusCode: statusCode,
    body: JSON.stringify({
      messages: result,
    }),
  };
};

module.exports.getSince = async (event) => {
  var sinceTime = event.query.from
  console.log("event", event);
  const message = JSON.stringify(event.body);
  console.log("message", message);
  // TODO:  validate event

  var params = {TableName: "kirc", ComparisonOperator: aws.String("BETWEEN"),
  AttributeValueList: []*dynamodb.AttributeValue {
     {
        S: aws.String(sinceTime),
     },
     {
        S: aws.String(date.now().toISOString()),
     },
  },}
  var statusCode, result;
  dynamoDB
      .scan(params, (err, data) => {
        if (err) {
          statusCode = 500
          result = err;
        }
        else {
          statusCode = 201
          result = data;
        }  
      })

  return {
    statusCode: statusCode,
    body: JSON.stringify({
      messages: result,
    }),
  };
};
