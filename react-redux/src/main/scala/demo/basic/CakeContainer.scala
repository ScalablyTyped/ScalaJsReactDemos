package demo.basic

import demo.ReduxFacade.Connected
import demo.basic.CakeActions.{BuyCake, CakeAction}
import demo.basic.CakeReducer.State
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CtorType, ScalaFnComponent}
import typings.redux.mod.Dispatch

import scala.scalajs.js

object CakeContainer {

  @js.native
  trait Props extends Connected[State, CakeAction] {
    val state: State
    val dispatch: Dispatch[CakeAction]
  }

  val component: Component[Props, CtorType.Props] = ScalaFnComponent[Props] { props =>
    <.div(
      <.h2(s"Number of cakes  ${props.state.numOfCakes}"),
      <.button(^.onClick --> Callback(props.dispatch(BuyCake())))("Buy Cake")
    )
  }

}
