const { readMessages } = require("./dynamodb")
// Handler
async function getmessages(event, context) {
  console.log('Started handling event', event)
  const messages = await readMessages()
  return {
    "statusCode": "200",
    "messages": messages.Items,
  }
  console.log('Finished handling event')
  context.succeed('Exit')
}

exports.getmessages = getmessages
