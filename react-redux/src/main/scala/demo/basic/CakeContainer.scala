package demo.basic

import demo.ReduxFacade.Connected
import demo.basic.CakeActions.{BuyCake, CakeAction}
import demo.basic.CakeReducer.State
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{CallbackTo, ScalaFnComponent}
import typings.redux.mod.Dispatch

import scala.scalajs.js

object CakeContainer {

  case class Props(numOfCakes: Int = 0, buyCake: () => Unit = () => ())

  val component = ScalaFnComponent[Connected[State, CakeAction] with Props] { props =>
    <.div(
      <.h2(s"Number of cakes - ${props.numOfCakes}"),
      <.button(^.onClick --> CallbackTo(props.buyCake()))("Buy Cake")
    )
  }

  val mapStateToProps: js.Function1[State, Props] =
    (state: State) => CakeContainer.Props(state.numOfCakes, () => ())

  val mapDispatchToProps: js.Function1[Dispatch[CakeAction], Props] =
    (dispatch: Dispatch[CakeAction]) => CakeContainer.Props(10, () => dispatch(BuyCake()))

}
