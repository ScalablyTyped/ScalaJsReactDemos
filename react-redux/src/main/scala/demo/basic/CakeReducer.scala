package demo.basic

import demo.basic.CakeActions.CakeAction
import typings.redux.mod.Reducer

import scala.scalajs.js

object CakeReducer {

  trait State extends js.Object {
    val numOfCakes: Int
  }

  object State {
    val initial: State = State(10)

    def apply(_numOfCakes: Int): State =
      new State { val numOfCakes: Int = _numOfCakes }
  }

  val Reducer: Reducer[State, CakeAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(State.initial)
    println(js.JSON.stringify(action))
    action match {
      case CakeActions.BuyCake(_) => State(state.numOfCakes - 1)
      case other =>
        state
    }
  }

}
