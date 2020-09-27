package demo.basic

import demo.ReduxFacade.Connected
import demo.basic.CakeActions.{BuyCake, CakeAction}
import demo.basic.CakeReducer.State
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import typings.redux.mod.Dispatch

import scala.scalajs.js

object CakeContainer {

  @js.native
  trait Props extends js.Object {
    var state: State = js.native
    var dispatch: Dispatch[CakeAction]
  }

  val component = ScalaFnComponent[Connected[State with js.Object, CakeAction] with Props] { props =>
    println(js.JSON.stringify(props))
    <.div(
      <.h2(s"Number of cakes  ${props.state.numOfCakes}"),
      <.button(^.onClick --> Callback(props.dispatch(BuyCake())))("Buy Cake")
    )
  }

}