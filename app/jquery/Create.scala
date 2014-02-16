package jquery

import DOM._

case class Create(
  override val selection:List[Element]
) extends JQuery
