package hello.world

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^.*
import typings.reactNative.mod.{TextStyle, ViewStyle}
import typings.reactNative.components.{Text, View}

val Home: Component[Unit, CtorType.Nullary] = ScalaFnComponent { case () =>
  View.style(ViewStyle().setMargin(20))(
    Text.style(TextStyle().setFontSize(16))(
      "This is a demo written in Scala through Scala.js, Slinky, ScalablyTyped and Expo.\n\n" +
        "It uses components from Antd Native and React Router Native."
    )
  )
}
