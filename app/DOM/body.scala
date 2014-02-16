package DOM

case class body(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "body"
  val selfClosing = false
}