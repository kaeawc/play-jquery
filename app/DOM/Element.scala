package DOM

trait Element {

  val attributes  : Map[String,String]
  val position    : Int
  val tag         : String
  val innerHtml   : String
  val selfClosing : Boolean
  private var _style       = Map[String,String]()

  private def style(key:String,value:String):Element = {

    if (_style.get(key).isDefined)
      _style -= key
    
    _style += (key -> value)

    this
  }

  def id = attributes.get("id")
  def name = attributes.get("name")
  def clazz = attributes.get("class")

  def show() = style("display","inline")

  def hide() = style("display","none")

  def style(key:String):Option[String] = _style.get(key)

  def getAttributes(start:Int,html:String) = {
    
    val tagEnd = html.indexOf('>',start)

  }
}
