package DOM

case class input(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "input"
  val selfClosing = true
}