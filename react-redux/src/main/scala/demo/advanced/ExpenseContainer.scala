package demo.advanced

import demo.advanced.ExpenseAction._
import demo.advanced.ExpenseReducer.AppState
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, Children, CtorType, JsComponent, ScalaFnComponent}
import typings.reactRedux.mod.{MapDispatchToPropsFunction, MapStateToProps, MapStateToPropsParam, connect}
import typings.redux.mod.{Action, AnyAction, Dispatch, bindActionCreators}

import scala.scalajs.js

object ExpenseContainer {

  class LinkStateProps(val expenses: js.Array[Expense]) extends js.Object

  object LinkStateProps {

    val initial: LinkStateProps = LinkStateProps(js.Array[Expense]())

    def apply(expenses: js.Array[Expense]): LinkStateProps =
      new LinkStateProps(expenses)
  }

  trait LinkDispatchProps extends js.Object {
    def startAddExpense(expense: Expense): Unit

    def startSetExpenses(expenses: js.Array[Expense]): Unit

    def startEditExpense(expense: Expense): Unit

    def startRemoveExpense(expenseId: String): Unit
  }

  type Props = LinkDispatchProps with LinkStateProps

  val component: Component[Props, CtorType.Props] = ScalaFnComponent[Props] { props =>
    <.div(
      <.h1(s"Expense Page"),
      <.div(
        props.expenses
          .map(expense =>
            <.div(
              <.p(expense.description),
              <.p(expense.amount),
              <.p(expense.note),
              <.button(^.onClick --> Callback(props.startRemoveExpense(expense.id)))("Remove Expense"),
              <.button(^.onClick --> Callback(props.startEditExpense(expense)))("Edit Expense")
            )
          )
          .toVdomArray,
      ),
      <.button(^.onClick --> Callback(props.startSetExpenses(js.Array[Expense](Expense()))))("Set Expense"),
      <.button(^.onClick --> Callback(props.startAddExpense(Expense())))("Add Expense"),
    )
  }

  val mapStateToProps: js.Function1[AppState, js.Dynamic] =
    (state: AppState) => {
      val expenses = state.asInstanceOf[js.Dynamic].expenseReducer.expenses
      js.Dynamic.literal(expenses = expenses)
    }


  val mapDispatchToProps: js.Function1[Dispatch[AppActions], LinkDispatchProps] =
    (dispatch: Dispatch[AppActions]) =>
      new LinkDispatchProps {
        override def startAddExpense(expense: Expense): Unit = dispatch(addExpense(expense)).asInstanceOf[Dispatch[AnyAction]]

        override def startSetExpenses(expenses: js.Array[Expense]): Unit = dispatch(setExpenses(expenses))

        override def startEditExpense(expense: Expense): Unit = dispatch(editExpense(expense))

        override def startRemoveExpense(expenseId: String): Unit = dispatch(removeExpense(expenseId))
      }

  val connectElem: Js.Component[Props, AppState with js.Object, CtorType.PropsAndChildren] =
    JsComponent[Props, Children.Varargs, AppState with js.Object](
      connect.asInstanceOf[js.Dynamic](mapStateToProps, mapDispatchToProps)(component.toJsComponent.raw)
    )

}
