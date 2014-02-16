package DOM

case class head(
  position   : Int,
  attributes : Map[String,String],
  innerHtml  : String = ""
) extends Element {

  val tag = "head"
  val selfClosing = false
}