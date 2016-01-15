import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import utils.LoadTestDefaults._

import scala.util.Random

class LanternAccessSimulation extends Simulation {

  val baseUrl = "http://lantern.ft.com"

  val rampUp = Integer.getInteger("ramp-up-seconds", DefaultRampUpDurationInSeconds)
  val numUsers = Integer.getInteger("users", DefaultNumUsers)
  val sessionID : String = System.getenv("ET_SESSION_ID")
  val initialPage = http.baseURL(baseUrl)

  def genericTest(testName: String, testUrl: String): ChainBuilder = {
    val urlConcat = baseUrl.concat(testUrl)

    val waitTime = new Random();

    val test = exec(addCookie(Cookie("connect.sid",sessionID)))
      .pause(waitTime.nextInt(10))
      .exec(http(testName)
        .get(testUrl)
        .check(currentLocation.is(urlConcat)))

    return test
  }

  object Home {
    val homeGet = "/" + "?PerfTest"
    val home = genericTest("Home",homeGet)
  }

  object Historical {
    val historicalGet = "/articles/9b66e747-6da4-3d0f-a189-c38d2997df10/global/FT" + "?PerfTest"
    val historical = genericTest("Historical",historicalGet)
  }

  object Realtime {
    val realtimeGet = "/realtime/articles/704162f8-b5f1-11e5-b147-e5e5bba42e51" + "?PerfTest"
    val realtime = genericTest("Realtime",realtimeGet)
  }

  object Sections {
    val sectionsGet = "/sections/Companies" + "?PerfTest"
    val sections = genericTest("Sections",sectionsGet)
  }

  object Topics{
    val topicsGet = "/topics/Driverless%20Cars" + "?PerfTest"
    val topics = genericTest("Topics",topicsGet)
  }

  val scnLantern = scenario("Lantern").exec(Home.home, Historical.historical, Realtime.realtime, Sections.sections, Topics.topics)

  setUp(
    scnLantern.inject(rampUsers(numUsers) over (rampUp seconds))
  ).protocols(initialPage)

}
