package DOM

case class Html(value:String) {

  val ids     = Html.findIds(this)
  val classes = Html.findClasses(this)
  val names   = Html.findNames(this)
  val tags    = Html.findTags(this)

}

object Html {

  val startTagRegex = "<[a-zA-Z]+".r
  val idRegex = "id=\"[a-zA-Z-_]*\"".r
  val classRegex = "class=\"[a-zA-Z-_ ]*\"".r
  val nameRegex = "name=\"[a-zA-Z-_ ]*\"".r
  val attrRegex = "[a-zA-Z]+=\"[()\"';:0-9a-zA-Z-_ ]*\"".r

  def findIds(implicit html:Html) = {

    val matches = idRegex findAllIn html.value

    matchesToList(matches)

  }

  def findClasses(implicit html:Html) = {

    val matches = classRegex findAllIn html.value

    matchesToList(matches)

  }

  def findNames(implicit html:Html) = {

    val matches = nameRegex findAllIn html.value

    matchesToList(matches)

  }

  def findTags(implicit html:Html) = {

    val matches = startTagRegex findAllIn html.value

    matchesToList(matches)

  }

  def matchesToList(matches:scala.util.matching.Regex.MatchIterator)(implicit html:Html) = {
    
    var start = 0

    matches.foldLeft(List[Element]()) {
      (list,identifier) =>

        val position = html.value.indexOf(identifier,start)
        start = html.value.lastIndexOf("<",position)
        val element = ElementOps.fromHtml(start)

        start = start + identifier.length

        // println(element)

        if (element.isDefined)
          list ::: List(element.get)
        else
          list
    }
  }
}