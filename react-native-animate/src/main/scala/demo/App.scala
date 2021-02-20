package demo

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CtorType, ScalaFnComponent}
import typings.react.mod.useState
import typings.reactNative.components.{Button, Image, Text, View}
import typings.reactNative.mod._
import typings.reactNative.reactNativeStrings.{center, column}

import scala.scalajs.js

object App {

  type Props = Unit

  type State = Int

  val component: Component[Props, CtorType.Nullary] = ScalaFnComponent[Props] { _ =>
    val js.Tuple2(state, setState) = useState[State](0)
    View.withProps(
      ViewProps().setStyle(
        ViewStyle()
          .setBackgroundColor("white")
          .setPadding(50)
          .setFlex(1)
          .setFlexDirection(column)
          .setJustifyContent(center)
          .setAlignItems(FlexAlignType.center)
      )
    )(
      Image(
        ImageURISource()
          .setUri("https://raw.githubusercontent.com/shadaj/slinky/master/logo.png")
      )
        .style(ImageStyle().setWidth(250).setHeight(250))
        .resizeMode(ImageResizeMode.cover),
      Text().style(
        TextStyle()
          .setFontSize(30)
          .setColor("red")
      )("Hello, Slinky Native!"),
      Text("Count: " + state),
      Button(
        title = "Tap Me!",
        onPress = _ =>
          Callback {
            setState(state + 1)
          }
      )
    )
  }
}
