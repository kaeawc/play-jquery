package DOM

import org.specs2.mutable.Specification

class documentSpec extends Specification {

  "document.write" should {

    "write directly to the DOM" in {

      implicit val dom = document.write("<html></html>")

      dom.value mustEqual "<html></html>"
    }
  }

  "document.getElementById" should {

    "return a valid element if it exists" in {

      implicit val dom = getBasicDivWithHello

      val element = document.getElementById("main")

      element must beSome
    }
  
    "return a valid element with its inner html" in {

      implicit val dom = getBasicDivWithSpan

      val element = document.getElementById("main")

      element match {
        case Some(div(0,attributes,innerHtml)) => {
          innerHtml.trim mustEqual """<span>Hello</span>"""
        }
        case Some(element:div) => failure("div does not match")
        case Some(element:Element) => failure("element found is not a div")
        case _ => failure("Element 'div' with id 'main' does exist.")
      }
    }
  }

  "document.getElementsByClassName" should {

    "find all elements with the 'post' tag in an html page" in {

      implicit val dom = getPosts

      val elements = document.getElementsByClassName("post")

      elements.length mustEqual 3
      
      elements.map {
        element => element.tag mustEqual "div"
      }
    }

    "find all elements with the 'open' tag in an html page" in {

      implicit val dom = getPosts

      val elements = document.getElementsByClassName("open")

      elements.length mustEqual 1
      
      elements(0).tag mustEqual "ul"
    }
  }

  "document.getElementsByName" should {

    "find all elements with the 'username' name in an html page" in {

      implicit val dom = getAuthForm

      val elements = document.getElementsByName("username")

      elements.length mustEqual 1
      
      elements.map {
        element => element.tag mustEqual "input"
      }
    }
  }

  "document.getElementsByTagName" should {

    "find all divs in an html page" in {

      implicit val dom = getPosts

      val elements = document.getElementsByTagName("div")

      elements.length mustEqual 17
      
      elements.map {
        element => element.tag mustEqual "div"
      }

      elements(0).id    must beSome("menu")
      elements(1).id    must beSome("main")
      elements(2).clazz must beSome("post")
      elements(3).clazz must beSome("title")
      elements(4).clazz must beSome("created")
      elements(5).clazz must beSome("author")
      elements(6).clazz must beSome("content")
    }

    "find all list items in an html page" in {

      implicit val dom = getPosts

      val elements = document.getElementsByTagName("li")

      elements.length mustEqual 3
      
      elements.map {
        element => element.tag mustEqual "li"
      }

      elements(0).innerHtml mustEqual "Home"
      elements(1).innerHtml mustEqual "About"
      elements(2).innerHtml mustEqual "Contact"
    }

    "return a valid element with its inner html" in {

      implicit val dom = getBasicDivWithSpan

      val elements = document.getElementsByTagName("div")

      elements.length mustEqual 1
      
      elements.head match {
        case div(position,attributes,innerHtml) => {
          attributes("id") mustEqual "main"
          innerHtml.trim mustEqual "<span>Hello</span>"
        }
        case _ => failure("Element 'div' with id 'main' does exist.")
      }
    }
  }

}
