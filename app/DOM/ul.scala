package DOM

case class ul(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "ul"
  val selfClosing = false
}