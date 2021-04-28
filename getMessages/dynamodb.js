const AWS = require('aws-sdk')
const dynamodb = new AWS.DynamoDB({ apiVersion: '2012-08-10' })
const tableName = 'kirc'

exports.getMessage = async function () {
  console.debug('Getting messages')

  let queryParams = {
    TableName: tableName,
    ScanIndexForward: false,
    Limit: 1
  };

  const messageCount = dynamodb.query(queryParams, function (err, data) {
    if (err) {
      console.error(err.message)
    } else {
      console.info('Received messages', data)
      return data
    }
  })

  const params = {
    Key: messageCount.id,
    TableName: tableName,
    ReturnConsumedCapacity: 'TOTAL',
  }

  return new Promise((resolve, reject) => {
    dynamodb.getItem(params, function (err, data) {
      if (err) {
        console.log('err', err)
        reject(err.message)
      } else {
        console.info('Received messages', data)
        resolve(data)
      }
    })
  })
}
