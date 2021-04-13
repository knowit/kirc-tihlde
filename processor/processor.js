const { sendMessage } = require("./mongodb")

// Handler
async function processor(event, context) {
  console.log('Started handling event', event)
  const messages = event.Records.map((record) => JSON.parse(record.body))
  const processedMessages = messages.map((message) => processMessage(message))

  await sendMessage(processedMessages)
  console.log('Finished handling event')
  context.succeed('Exit')
}

const processMessage = function (messageAsString) {
  const message = JSON.parse(messageAsString)
  return {
    message: message.message,
    timestamp: processTimestamp(message.timestamp),
    id: message.id,
    nickname: message.nickname,
    style: message.style,
  }
}


function processTimestamp(timestampAsString) {
  const date = new Date(timestampAsString)
  return date.toISOString()
}

exports.processMessage = processMessage
exports.processor = processor
