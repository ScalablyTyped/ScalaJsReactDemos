package demo

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^.*
import typings.node.global.module
import typings.storybookReact.mod.storiesOf

object Demo:
  def main(args: Array[String]): Unit =
    storiesOf("Button", module)
      .add("with text", ctx => <.button("Hello Button").rawElement)
      .add(
        "with some emoji",
        ctx =>
          <.button(
            ^.onClick ==> (e => Callback.alert(s"x: ${e.pageX}, y: ${e.pageY}")),
            ^.aria.label := "so cool",
            ^.role := "img"
          )(<.span("😀😎")).rawElement
      )
end Demo
