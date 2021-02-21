package demo.advanced

import demo.advanced.ExpenseAction._
import demo.advanced.ExpenseReducer.AppState
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, Children, CtorType, JsComponent, ScalaFnComponent}
import typings.reactRedux.mod.connect
import typings.redux.mod.Dispatch

import scala.scalajs.js

object ExpenseContainer {

  class LinkStateProps(val expenses: js.Array[Expense]) extends js.Object

  object LinkStateProps {

    val initial: LinkStateProps = LinkStateProps(js.Array[Expense]())

    def apply(expenses: js.Array[Expense]): LinkStateProps =
      new LinkStateProps(expenses)
  }

  trait LinkDispatchProps extends js.Object {
    val startAddExpense: js.Function1[Expense, AppActions]
    val startSetExpenses: js.Function1[js.Array[Expense], AppActions]
    val startEditExpense: js.Function1[Expense, AppActions]
    val startRemoveExpense: js.Function1[String, AppActions]
  }

  type Props = LinkDispatchProps with LinkStateProps

  val component: Component[Props, CtorType.Props] = ScalaFnComponent[Props] { props =>
    <.div(
      <.h1(s"Expense Page"),
      <.div(
        props.expenses
          .map(expense =>
            <.div(^.key := expense.id)(
              <.p(expense.description),
              <.p(expense.amount),
              <.p(expense.note),
              <.button(^.onClick --> Callback(props.startRemoveExpense(expense.id)))("Remove Expense"),
              <.button(^.onClick --> Callback(props.startEditExpense(expense)))("Edit Expense")
            )
          )
          .toVdomArray
      ),
      <.button(^.onClick --> Callback(props.startSetExpenses(js.Array[Expense](Expense()))))("Set Expense"),
      <.button(^.onClick --> Callback(props.startAddExpense(Expense())))("Add Expense")
    )
  }

  val mapStateToProps: js.Function1[AppState, js.Dynamic] =
    (state: AppState) => {
      val expenses: js.Array[Expense] =
        state.asInstanceOf[js.Dynamic].expenseReducer.expenses.asInstanceOf[js.Array[Expense]]
      js.Dynamic.literal(expenses = expenses)
    }

  val mapDispatchToProps: js.Function1[Dispatch[AppActions], LinkDispatchProps] =
    (dispatch: Dispatch[AppActions]) =>
      new LinkDispatchProps {
        override val startAddExpense: js.Function1[Expense, AppActions] =
          expense => dispatch(addExpense(expense))
        // https://stackoverflow.com/questions/41754489/when-would-bindactioncreators-be-used-in-react-redux
        // bindActionCreators(addExpense, dispatch.asInstanceOf[Dispatch[AnyAction]])

        override val startSetExpenses: js.Function1[js.Array[Expense], AppActions] =
          expenses => dispatch(setExpenses(expenses))
        // bindActionCreators(setExpenses, dispatch.asInstanceOf[Dispatch[AnyAction]])

        override val startEditExpense: js.Function1[Expense, AppActions] =
          expense => dispatch(editExpense(expense))
        // bindActionCreators(editExpense, dispatch.asInstanceOf[Dispatch[AnyAction]])

        override val startRemoveExpense: js.Function1[String, AppActions] =
          expenseId => dispatch(removeExpense(expenseId))
        // bindActionCreators(removeExpense, dispatch.asInstanceOf[Dispatch[AnyAction]])
      }

  val connectElem: Js.Component[Props, AppState with js.Object, CtorType.PropsAndChildren] =
    JsComponent[Props, Children.Varargs, AppState with js.Object](
      connect.asInstanceOf[js.Dynamic](mapStateToProps, mapDispatchToProps)(component.toJsComponent.raw)
    )

}
