const { MongoClient } = require('mongodb')
const { getSecret } = require('./secrets')
const index = 'Message'
const secretName = 'kirc-documentdb-credentials'
const mongoOptions = {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  directConnection: true,
}
let cachedCollection = null

async function sendMessage(messages) {
  console.log('Sending', messages)
  const collection = await connectToDatabase()
  console.log('collection', collection)
  const result = await collection.insertMany(messages)
  console.debug(`Inserted ${result.insertedCount} messages`, messages)
  const totalDocumentCount = await cachedCollection.countDocuments()
  console.debug(`There are ${totalDocumentCount} documents in the collection`)
}

async function connectToDatabase() {
  if (!cachedCollection) {
    console.log('Connection not yet established. Connecting.')
    const { username, password, host, port, ssl } = await getSecret(secretName)
    const connectionString = `mongodb://${username}:${password}@${host}:${port}/?ssl=${ssl}&replicaSet=rs0&readPreference=secondaryPreferred&retryWrites=false`
    console.log('connectionString', connectionString)

    const connectedClient = await new MongoClient(
      connectionString,
      mongoOptions,
    ).connect()
    console.log('Connection acquired')
    const collection = connectedClient.db(index).collection(index)
    cachedCollection = collection

    return cachedCollection
  } else {
    console.log('Connection found. Returning')
    return cachedCollection
  }
}

exports.sendMessage = sendMessage
