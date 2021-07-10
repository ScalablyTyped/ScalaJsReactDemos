package demo.facade

import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import typings.reactNative.components.Text.Builder
import typings.reactNative.mod.TextProps
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AnimatedText {

  @JSImport("react-native", "Animated.Text")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait AnimatedTextProps extends js.Object {}

  @scala.inline
  def apply(): Builder = {
    val __props = js.Dynamic.literal()
    new Builder(js.Array(this.RawComponent, __props.asInstanceOf[TextProps]))
  }

  val component: Js.Component[AnimatedTextProps, Null, CtorType.PropsAndChildren] =
    JsComponent[AnimatedTextProps, Children.Varargs, Null](RawComponent)

}
