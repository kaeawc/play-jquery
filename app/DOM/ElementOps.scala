package DOM

object ElementOps {

  def tagName(start:Int)(implicit html:Html) = {

    val tagStart = start + 1
    val space = html.value.indexOf(' ',tagStart)
    val end = html.value.indexOf('>',tagStart)
    
    val tagEnd =
      if (end > space && space > 0)
        space
      else
        end

    html.value.substring(tagStart,tagEnd)

  }

  def getInnerHtml(start:Int,tag:String)(implicit html:Html) = {

    var closingTag = start + tag.length

    var opened = 1

    while (opened > 0) {

      val open = html.value.indexOf("<",closingTag)
      val close = html.value.indexOf("</",closingTag)
      val selfClose = html.value.indexOf("/>",closingTag)

      val lastOpened = opened

      val scriptOpen = html.value.indexOf("<script",closingTag)
      val scriptClose = html.value.indexOf("</script",closingTag)
      val styleOpen = html.value.indexOf("<style",closingTag)
      val styleClose = html.value.indexOf("</style",closingTag)

      closingTag =
        if (selfClose >= 0)
          if(open >= 0 && open < selfClose)
            if (close >= 0 && close <= open) {
              opened = opened - 1
              close + 2
            } else {
              opened = opened + 1
              open + 1
            }
          else {
            opened = opened - 1
            selfClose + 2
          }
        else if(open >= 0)
          if (close >= 0 && close <= open) {
            opened = opened - 1
            close + 2
          } else {
            opened = opened + 1
            open + 1
          }
        else if (close >= 0) {
          opened = opened - 1
          close + 2
        } else throw new Exception("Corrupt HTML")

        if (scriptOpen  >= 0 && scriptOpen  < closingTag ||
          scriptClose >= 0 && scriptClose < closingTag ||
          styleOpen   >= 0 && styleOpen   < closingTag ||
          styleClose  >= 0 && styleClose  < closingTag)
          opened = lastOpened

    }

    val innerStart = html.value.indexOf('>',start) + 1
    val innerEnd = closingTag - 2

    if (innerStart >= innerEnd) ""
    else html.value.substring(innerStart,innerEnd)
  }

  def getAttributes(start:Int)(implicit html:Html) = {

    val close = html.value.indexOf(">",start)
    val selfClose = html.value.indexOf("/>",start)

    val end =
      if (close < selfClose)
        close
      else if (selfClose >= 0)
        selfClose
      else
        close

    val entireTag = html.value.substring(start + 1,end)

    val matches = Html.attrRegex findAllIn entireTag

    matches.foldLeft(scala.collection.mutable.Map[String,String]()) {
      (attributes,identifier) =>

        val parts = identifier.split('=')

        val key = parts(0)

        val concat = parts.foldLeft(new StringBuilder("")) {
          (value,part) =>
            if (part != key)
              value.append(part)
            else
              value
        }

        val value = concat.substring(1,concat.length - 1)

        attributes += (key -> value)
    }
  }

  def fromHtml(start:Int)(implicit html:Html):Option[Element] = {

    val tag = tagName(start)

    val innerHtml = getInnerHtml(start,tag)

    val attributes = Map[String,String]() ++ getAttributes(start)

    val element = tag match {
      case "div"   => Some(div(start,attributes,innerHtml))
      case "body"  => Some(body(start,attributes,innerHtml))
      case "span"  => Some(span(start,attributes,innerHtml))
      case "head"  => Some(head(start,attributes,innerHtml))
      case "ul"    => Some(ul(start,attributes,innerHtml))
      case "li"    => Some(li(start,attributes,innerHtml))
      case "input" => Some(input(start,attributes,innerHtml))
      case "img"   => Some(img(start,attributes,innerHtml))
      case _ => None
    }
    
    element
  }
}
