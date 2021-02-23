package demo.facade

import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import japgolly.scalajs.react.component.Js

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AnimatedView {

  @JSImport("react-native", "Animated.View")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait AnimatedProps extends js.Object {}

  val component: Js.Component[AnimatedProps, Null, CtorType.PropsAndChildren] =
    JsComponent[AnimatedProps, Children.Varargs, Null](RawComponent)

}
