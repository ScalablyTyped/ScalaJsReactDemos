package demo.facade

import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import typings.reactNative.mod.ViewProps

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AnimatedView {

  @JSImport("react-native", "Animated.View")
  @js.native
  object RawComponent extends js.Object

  val component: Js.Component[ViewProps, Null, CtorType.PropsAndChildren] =
    JsComponent[ViewProps, Children.Varargs, Null](RawComponent)

}
