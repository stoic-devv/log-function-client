package requests
import org.apache.http.client.HttpResponseException
import org.apache.http.{HttpEntity, StatusLine}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}
import org.mockito.{ArgumentMatchers, Mockito}
import org.scalatestplus.mockito.MockitoSugar

class AwsRequestTestSuite extends BaseTestSpec with MockitoSugar {

  val httpClient = mock[DefaultHttpClient]
  val response = mock[CloseableHttpResponse]
  val statusLine = mock[StatusLine]
  val httpGet = mock[HttpGet]

  behavior of "sending http request"

  it should "successfully for a running server" in {

    Mockito.when(httpClient.execute(httpGet)).thenReturn(response)
    Mockito.when(response.getStatusLine()).thenReturn(statusLine)
    Mockito.when(statusLine.getStatusCode()).thenReturn(200)

    val methodResponse = httpClient.execute(httpGet)
    assert(methodResponse.getStatusLine.getStatusCode == 200)
  }

  it should "throw an exception on execute" in {

    Mockito.when(httpClient.execute(httpGet)).thenThrow(classOf[HttpResponseException])
    Mockito.when(response.getStatusLine()).thenReturn(statusLine)

    val except = the [HttpResponseException] thrownBy httpClient.execute(httpGet)
  }

}
