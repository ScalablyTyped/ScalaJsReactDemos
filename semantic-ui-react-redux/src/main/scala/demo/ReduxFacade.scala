package demo

import typings.redux.mod.{Action, Dispatch}

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


}
