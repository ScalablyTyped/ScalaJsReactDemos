package hello.world

import scala.scalajs.js.annotation.JSExportTopLevel

object Main:
  @JSExportTopLevel("app")
  val app = LoadFonts.component.toJsComponent.raw
