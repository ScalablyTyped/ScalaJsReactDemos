package demo.basic

import demo.basic.CakeAction.CakeAction
import typings.redux.mod.Reducer

import scala.scalajs.js

object CakeReducer {

  class State(val numOfCakes: Int) extends js.Object

  object State {

    val initial: State = State(10)

    def apply(_numOfCakes: Int): State =
      new State(_numOfCakes)
  }

  val Reducer: Reducer[State, CakeAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(State.initial)
    action match {
      case CakeAction.BuyCake() => State(state.numOfCakes - 1)
      case _                     => state
    }
  }
}
