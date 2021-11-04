package requests

import org.apache.http.client.HttpResponseException
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}


object AwsRequest:

  import org.apache.http.client.methods.CloseableHttpResponse
  import org.apache.http.client.methods.HttpGet
  import org.apache.http.client.methods.HttpRequestBase
  import org.apache.http.client.utils.URIBuilder
  import java.net.URI

  def sendHttpRequest: String = {
    val httpClient = buildHttpClient()
    val httpGet = new HttpGet("https://uvz1k07zeg.execute-api.us-east-2.amazonaws.com/prod/logs")
    val uri = new URIBuilder(httpGet.getURI).addParameter("t", "???")
      .addParameter("dt", "00:00:10.00").build
    httpGet.asInstanceOf[HttpRequestBase].setURI(uri)
    try {
      val response = httpClient.execute(httpGet)
      BasicResponseHandler().handleResponse(response)
    } catch {
      case e: HttpResponseException => {
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


