provider "aws" {
    region = "eu-central-1"
    skip_get_ec2_platforms = true
}
resource "aws_sqs_queue" "message_queue" {
  name = "message_queue.fifo"
  delay_seconds = 10
  max_message_size = 10240
  message_retention_seconds = 345600
  receive_wait_time_seconds = 10
  fifo_queue = true
  content_based_deduplication = true
}

