package requests

import constants.LambdaConstants
import org.apache.http.client.HttpResponseException
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}
import utils.{CreateLogger, ObtainConfigReference}


object AwsRequest:

  import org.apache.http.client.methods.CloseableHttpResponse
  import org.apache.http.client.methods.HttpGet
  import org.apache.http.client.methods.HttpRequestBase
  import org.apache.http.client.utils.URIBuilder
  import java.net.URI

  val logger = CreateLogger(classOf[AwsRequest.type])
  val config = ObtainConfigReference(LambdaConstants.CONF_ENTRY) match {
    case Some(value) => value.getConfig(LambdaConstants.CONF_ENTRY)
    case None => throw new RuntimeException("Cannot obtain a reference to the config data.")
  }

  def sendHttpRequest(tm: String, tmInterval: String): String = {
    val httpClient = buildHttpClient()
    val httpGet = new HttpGet(config.getString(LambdaConstants.URL))
    val uri = new URIBuilder(httpGet.getURI).addParameter(LambdaConstants.TIME_PARAM, tm)
      .addParameter(LambdaConstants.INTERVAL_PARAM, tmInterval).build
    httpGet.asInstanceOf[HttpRequestBase].setURI(uri)
    try {
      logger.info("Executing client request to API Gateway")
      val response = httpClient.execute(httpGet)
      BasicResponseHandler().handleResponse(response)
    } catch {
      case e: HttpResponseException => {
        logger.error(s"Request failed with error ${e.getStatusCode()} - ${e.getReasonPhrase()}")
        s"${e.getStatusCode()} - ${e.getReasonPhrase()}"
      }
    }
  }

  private def buildHttpClient():

  DefaultHttpClient = {
    val httpClient = new DefaultHttpClient
    val httpParams = httpClient.getParams
    httpClient.setParams(httpParams)
    httpClient
  }


