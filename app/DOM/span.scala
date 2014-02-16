package DOM

case class span(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "span"
  val selfClosing = false
}