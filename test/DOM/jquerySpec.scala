package jquery

import DOM._
import jquery._

import org.specs2.mutable.Specification

class jquerySpec extends Specification {

  "JQuery('div#main')" should {

    "select a div element with id equal to 'main'" in {

      implicit val dom = getBasicDivWithHello

      val main:List[Element] = JQuery.find("div#main")

      main.length mustEqual 1

      main(0).tag mustEqual "div"
      main(0).id must beSome("main")
    }
  }
}
