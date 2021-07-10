package hello.world

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import typings.reactNative.mod.{TextStyle, ViewStyle}
import typings.reactNative.components.{Text, View}

object Home:
  type Props = Unit

  val component = ScalaFnComponent[Props] { case () =>
    View.style(ViewStyle().setMargin(20))(
      Text.style(TextStyle().setFontSize(16))(
        "This is a demo written in Scala through Scala.js, Slinky, ScalablyTyped and Expo.\n\n" +
          "It uses components from Antd Native and React Router Native."
      )
    )
  }
end Home
