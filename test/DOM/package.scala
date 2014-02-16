package object DOM {

  def getBlank             = Html(views.html.blank().toString)
  def getPosts             = Html(views.html.posts().toString)
  def getAuthForm          = Html(views.html.auth().toString)
  def getBasicDiv          = Html(views.html.basicdiv().toString)
  def getBasicDivWithHello = Html(views.html.basicDivWithHello().toString)
  def getBasicDivWithSpan  = Html(views.html.basicDivWithSpan().toString)
  def getBootstrap         = Html(views.html.bootstrap().toString)

}
