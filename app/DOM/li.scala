package DOM

case class li(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "li"
  val selfClosing = false
}