package demo

import demo.advanced.{ExpenseAction, ExpenseContainer, ExpenseReducer, ExpenseState}
import demo.basic.CakeAction.CakeAction
import demo.basic.{CakeContainer, CakeReducer}
import japgolly.scalajs.react.{Children, JsComponent}
import org.scalajs.dom
import typings.reactRedux.components.Provider
import typings.reactRedux.mod.connect
import typings.redux.mod.*
import typings.reduxDevtoolsExtension.developmentOnlyMod.composeWithDevTools

import scala.scalajs.js
import scala.scalajs.js.|

object Main:
  type AppAction = ExpenseAction | CakeAction

  trait AppState extends js.Object:
    val expenses: ExpenseState
    val cake:     CakeReducer.State

  trait Reducers extends js.Object:
    val expenses: Reducer[ExpenseState, ExpenseAction]
    val cake:     Reducer[CakeReducer.State, CakeAction]

  val rootReducer: Reducer[AppState, AppAction] =
    combineReducers(new Reducers:
      override val expenses: Reducer[ExpenseState, ExpenseAction]   = ExpenseReducer.Reducer
      override val cake:     Reducer[CakeReducer.State, CakeAction] = CakeReducer.Reducer
    ).asInstanceOf[Reducer[AppState, AppAction]]

  def main(args: Array[String]): Unit =
    val Empty = js.Object

    val store = createStore(rootReducer, composeWithDevTools(applyMiddleware()))
    val keepDispatch: js.Function1[Dispatch[AppAction], js.Dynamic] = d => js.Dynamic.literal(dispatch = d)

    val ConnectedExpenses = JsComponent[js.Object, Children.None, js.Object](
      connect
        .asInstanceOf[js.Dynamic]((appState: AppState) => js.Dynamic.literal(state = appState.expenses), keepDispatch)(
          ExpenseContainer.component.toJsComponent.raw
        )
    )

    val ConnectedCakes = JsComponent[js.Object, Children.None, js.Object](
      connect.asInstanceOf[js.Dynamic]((appState: AppState) => js.Dynamic.literal(state = appState.cake), keepDispatch)(
        CakeContainer.component.toJsComponent.raw
      )
    )

    Provider(store.unsafeCast2[Any, AppAction])(
      ConnectedExpenses(Empty),
      ConnectedCakes(Empty)
    ).renderIntoDOM(dom.document.getElementById("container"))
  end main
end Main
