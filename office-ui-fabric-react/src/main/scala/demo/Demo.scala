package demo

import org.scalajs.dom
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^.*
import typings.officeUiFabricReact.components as Fabric
import typings.react.mod.useState

import scala.scalajs.js

val App = ScalaFnComponent[String] { name =>
  /* use a hook to keep state */
  val js.Tuple2(state, setState) = useState(1)

  val incrementButton = Fabric.Button.onClick(_ => Callback(setState(state + 1)))(s"Increment it, $name")
  val text            = Fabric.TextField.value(state.toString).disabled(true)
  <.div(text, incrementButton)
}

@main
def main: Unit =
  App("Dear user").renderIntoDOM(dom.document.getElementById("container"))
