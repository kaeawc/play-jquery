import DOM._

package object jquery {

  def tagEnd(
    start:Int,
    html:String
  ) = {

    html.indexOf(">",start)

  }

  // def tagName(
  //   start:Int,
  //   html:String
  // ) = {

  //   html.substring(start + 1,html.indexOf(" ",start))

  // }

  def tagStart(
    start:Int,
    html:String
  ) = {

    html.lastIndexOf("<",start)

  }

  // def closingTag(
  //   name:String,
  //   start:Int,
  //   html:String
  // ) = {

  // }
}