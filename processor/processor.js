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

  json_style = JSON.parse(message.style);
  let style_output = '';
  // for (const [key, value] of Object.entries(json_style)){
  //   style_output += `${key}: ${value};`
  // }
  json_style.forEach(e => {
    style_output += `${e[key]}: ${e[value]};`    
  });

  return {
    message: message.message,
    timestamp: new Date().toISOString(),
    id: message.id,
    nickname: message.nickname,
    style: style_output,
  }
}

exports.processMessage = processMessage
exports.processor = processor