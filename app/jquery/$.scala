package jquery

import DOM._

case class $(implicit 
  select : String,
  html   : Html
) {

  val selection:List[Element] = JQuery.find(select)

}