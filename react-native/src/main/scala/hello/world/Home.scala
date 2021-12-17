package hello.world

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import typings.reactNative.components.{Text, View}
import typings.reactNative.mod.{TextStyle, ViewStyle}

val Home: Component[Unit, CtorType.Nullary] = ScalaFnComponent { case () =>
  View.style(ViewStyle().setMargin(20))(
    Text.style(TextStyle().setFontSize(16))(
      "This is a demo written in Scala through Scala.js, Slinky, ScalablyTyped and Expo.\n\n" +
        "It uses components from Antd Native and React Router Native."
    )
  )
}
