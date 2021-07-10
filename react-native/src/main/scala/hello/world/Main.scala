package hello.world

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("app")
val app = LoadFonts.component.toJsComponent.raw
