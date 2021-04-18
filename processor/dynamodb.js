const AWS = require('aws-sdk')
const dynamodb = new AWS.DynamoDB({ apiVersion: '2012-08-10' })
const tableName = 'kirc'

exports.sendMessage = async function (message) {
  console.debug('Saving message', message)
  const formattedMessage = formatMessage(message)
  const params = {
    Item: formattedMessage,
    TableName: tableName,
    ReturnConsumedCapacity: 'TOTAL',
  }
  return new Promise((resolve, reject) => {
    dynamodb.putItem(params, function (err, data) {
      if (err) {
        console.log('err', err)
        reject(err.message)
      } else {
        console.info('Saved message', message.id)
        console.debug("data", data)
        resolve('ok')
      }
    })
  })
}

function formatMessage(message) {
  const object = {}
  object.id = { S: message.id }
  object.message = { S: message.message }
  object.timestamp = { S: message.timestamp }
  object.nickname = { S: message.nickname }
  object.style = { S: message.style }
  return object
}
