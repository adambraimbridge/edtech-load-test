package testChains

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import utils.ConfigLoader

object PickOfTheDay {

  val url = "/thehighlights" + ConfigLoader.perfTestID
  val urlConcat = ConfigLoader.baseUrl.concat(url)

  def runner(): ChainBuilder = {
    exec(addCookie(Cookie("connect.sid", ConfigLoader.sessionID)))
      .exec(http("Page: Pick of the Day")
        .get(url)
        .check(currentLocation.is(urlConcat))
        .check(css("#react-app"))
        .check(status.is(200)))
  }
}
