package demo.facade

import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import japgolly.scalajs.react.component.Js
import typings.reactNative.components.SharedBuilder_ViewProps39190290

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AnimatedView {

  @JSImport("react-native", "Animated.View")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait AnimatedViewProps extends js.Object {}

  val component: Js.Component[AnimatedViewProps, Null, CtorType.PropsAndChildren] =
    JsComponent[AnimatedViewProps, Children.Varargs, Null](RawComponent)


}
