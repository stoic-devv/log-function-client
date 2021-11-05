import ClientApplication.logger
import requests.AwsRequest
import utils.CreateLogger

object ClientApplication:
  val logger = CreateLogger(classOf[ClientApplication.type])

  def main(args: Array[String]): Unit =
    args.length match {
      case 2 => {}
      case _ => throw new RuntimeException("Invalid number of arguments")
    }
    println(AwsRequest.sendHttpRequest(args(0), args(1)))
