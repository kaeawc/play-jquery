package DOM

case class img(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "img"
  val selfClosing = true
}