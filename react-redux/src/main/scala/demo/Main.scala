package demo

import demo.advanced.ExpenseContainer.{LinkStateProps, Props}
import demo.advanced.{Expense, ExpenseContainer, ExpenseStore}
import demo.basic.CakeActions.CakeAction
import demo.basic.{CakeContainer, CakeStore, ReduxFacade}
import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.component.Js.Component
import org.scalajs.dom
import typings.reactRedux.components.Provider

import scala.scalajs.js

object Main {

  // BASIC
  // https://www.youtube.com/watch?v=gFZiQnM3Is4
//  def main(args: Array[String]): Unit = {
//
//    val ConnectedDemo: Component[CakeContainer.Props, Null, CtorType.PropsAndChildren] =
//      ReduxFacade.simpleConnect(CakeStore.Store, CakeContainer.component)
//
//    Provider[CakeAction](CakeStore.Store)(
//        ConnectedDemo({
//          val props = (new js.Object).asInstanceOf[CakeContainer.Props]
//          props
//        })()
//    ).renderIntoDOM(dom.document.getElementById("container"))
//  }

  // ADVANCED
  // https://www.youtube.com/watch?v=OXxul6AvXNs
  // https://github.com/cmcaboy/redux-typed/tree/typed
  def main(args: Array[String]): Unit =
    Provider(ExpenseStore.value)(
      ExpenseContainer.connectElem {
        val props = (new js.Object()).asInstanceOf[Props]
        props
      }()
    ).renderIntoDOM(dom.document.getElementById("container"))

}
