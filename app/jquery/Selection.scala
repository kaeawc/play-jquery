package jquery

import DOM._

case class Selection(
  override val selection:List[Element]
) extends JQuery
