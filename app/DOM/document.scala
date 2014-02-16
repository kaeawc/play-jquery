package DOM

object document {

  def write(value:String):Html = Html(value)

  def getElementById(query:String)(implicit html:Html):Option[Element] = {

    for (element <- html.ids) {
      if(element.id.isDefined && element.id.get == query) {
        return Some(element)
      }
    }

    None
  }

  def getElementsByClassName(query:String)(implicit html:Html):List[Element] = {

    html.classes.foldLeft(List[Element]()) {
      (list,element) =>
        
        if(element.clazz.isDefined && element.clazz.get == query)
          list ::: List(element)
        else
          list
    }
  }

  def getElementsByName(query:String)(implicit html:Html):List[Element] = {

    html.names.foldLeft(List[Element]()) {
      (list,element) =>
        
        if(element.name.isDefined && element.name.get == query)
          list ::: List(element)
        else
          list
    }
  }

  def getElementsByTagName(query:String)(implicit html:Html):List[Element] = {

    html.tags.foldLeft(List[Element]()) {
      (list,element) =>
        
        if(element.tag == query)
          list ::: List(element)
        else
          list
    }
  }

  def getElementsByTagNameNS(
    name:String
  )(implicit
    html:Html
  ):List[Element] = {

    Nil

  }


}