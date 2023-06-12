package demo

import japgolly.scalajs.react.component.JsFn.UnusedObject
import japgolly.scalajs.react.raw.React.StatelessFunctionalComponent

import scala.scalajs.js.annotation.JSExportTopLevel

// yarn --cwd react-native-animations/ expo start
// sbt "project react-native-animations;clean; ~fastOptJS"
object Main {

  @JSExportTopLevel("app")
  val app: StatelessFunctionalComponent[UnusedObject] = Drawer.component.toJsComponent.raw

}
