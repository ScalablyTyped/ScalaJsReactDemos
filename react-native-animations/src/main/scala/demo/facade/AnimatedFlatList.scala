package demo.facade

import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import typings.reactNative.components.FlatList.Builder
import typings.reactNative.mod.FlatListProps

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AnimatedFlatList {

  @JSImport("react-native", "Animated.FlatList")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait AnimatedFlatListProps extends js.Object {}

  @scala.inline
  def apply[ItemT](): Builder[ItemT] = {
    val __props = js.Dynamic.literal()
    new Builder[ItemT](js.Array(this.RawComponent, __props.asInstanceOf[FlatListProps[ItemT]]))
  }

  val component: Js.Component[AnimatedFlatListProps, Null, CtorType.PropsAndChildren] =
    JsComponent[AnimatedFlatListProps, Children.Varargs, Null](RawComponent)

}
