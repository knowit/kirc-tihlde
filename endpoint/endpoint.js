"use strict";
const AWS = require("aws-sdk");
const sqs = new AWS.SQS({ apiVersion: "2012-11-05" });

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
