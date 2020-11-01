package demo.advanced

import demo.advanced.ExpenseReducer
import typings.redux.mod.{applyMiddleware, createStore}
import typings.reduxDevtoolsExtension.developmentOnlyMod.composeWithDevTools

object ExpenseStore {
  val value =
    createStore(ExpenseReducer.rootReducer, composeWithDevTools())
}
