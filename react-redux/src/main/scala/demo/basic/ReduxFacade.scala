package demo.basic

import japgolly.scalajs.react.component.{Js, ScalaFn}
import japgolly.scalajs.react.{Children, CtorType, JsComponent}
import typings.reactRedux.mod.connect
import typings.redux.mod.{Action, Dispatch, Store}

import scala.scalajs.js

/**
  * This is very rudimentary, just enough to support the demo
  */
object ReduxFacade {

  /** Since redux forces us to use plain js objects,
    *  this is the only trivially extractable boilerplate
    */
  trait Extractor[T] {
    protected val _type: String
    def unapply(a: Action[String]): Option[T] =
      if (a.`type` == _type) Some(a.asInstanceOf[T]) else None
  }

  trait Connected[State, Action] extends js.Object {
    val dispatch: Dispatch[Action]
    val state: State
  }

  /* take a store and a component which takes `dispatch` and `state` as props, return a component with those filled */
  def simpleConnect[State <: js.Any, Action <: js.Any, P <: js.Object](
      store: Store[State, Action],
      c: ScalaFn.Component[Connected[State, Action] with P, CtorType.Props]
  ): Js.Component[P, Null, CtorType.PropsAndChildren] = {

    val keepState: js.Function1[State, js.Dynamic] = s => js.Dynamic.literal(state = s)

    val keepDispatch: js.Function1[Dispatch[Action], js.Dynamic] = d => js.Dynamic.literal(dispatch = d)

    JsComponent[P, Children.Varargs, Null](
      connect.asInstanceOf[js.Dynamic](keepState, keepDispatch)(c.toJsComponent.raw)
    )

  }

}
