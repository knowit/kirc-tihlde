const { sendMessage } = require("./dynamodb")
// Handler
async function processor(event, context) {
  console.log('Started handling event', event)
  const message = JSON.parse(event.Records[0].body)
  const processedMessage = processMessage(message)
  await sendMessage(processedMessage)
  console.log('Finished handling event')
  context.succeed('Exit')
}

const processMessage = function (messageAsString) {
  const message = JSON.parse(messageAsString)
  const style = JSON.parse(message.style)
  const styleStr = Object.keys(style).map(key => {
    styleStr += `${key}: ${style[key]};`
  }).join(' ')

  return {
    message: message.message,
    timestamp: new Date().toISOString(),
    id: message.id,
    nickname: message.nickname,
    style: styleStr,
  }
}

exports.processMessage = processMessage
exports.processor = processor
