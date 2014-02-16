package jquery

import DOM._

trait JQuery {

  val selection:List[Element] = Nil

  def show(implicit
    elements : List[Element],
    html     : Html
  ):JQuery = {
    
    val result = elements map {
      element => element.show()
    }

    Selection(result)
  }

  def find(select:String)(implicit html:Html):List[Element] = {

    val jqueryRegex = "(?:\\s*(<[\\w\\W]+>)[^>]*|[#\\.]([\\w-]*))".r

    val matches = (jqueryRegex findAllIn select).toList

    if (matches.length == 0) 
      Nil
    else {
      matches.foldLeft(List[Element]()) {
        (list,identifier) =>

          if (identifier == "") 
            list
          else {

            val kind = identifier.charAt(0)
            val name = identifier.substring(1,identifier.length)
            
            val selected:List[Element] = kind match {
              case '#' =>
                document.getElementById(name) match {
                  case Some(element:Element) => List(element)
                  case _ => Nil
                }
              case '.' => document.getElementsByClassName(name)
              case ' ' => document.getElementsByTagName(name)
            }

            list ::: selected
          }
      }
    }
  }
}

object JQuery extends JQuery