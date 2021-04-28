const AWS = require('aws-sdk')
const dynamodb = new AWS.DynamoDB({ apiVersion: '2012-08-10' })
const tableName = 'kirc'

exports.readMessages = async function () {
  console.debug('Getting messages', message)
  const params = {
    TableName: tableName,
  }
  return new Promise((resolve, reject) => {
    dynamodb.scan(params, function (err, data) {
      if (err) {
        console.log('err', err)
        reject(err.message)
      } else {
        console.info('Read messages', message.id)
        console.debug('data', data)
        resolve(data)
      }
    })
  })
}

