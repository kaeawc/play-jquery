package DOM

case class div(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag         = "div"
  val selfClosing = false
}