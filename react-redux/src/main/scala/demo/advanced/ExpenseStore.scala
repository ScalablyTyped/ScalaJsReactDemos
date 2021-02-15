package demo.advanced

import typings.redux.mod.{applyMiddleware, createStore}
import typings.reduxDevtoolsExtension.developmentOnlyMod.composeWithDevTools

object ExpenseStore {
  val value =
    createStore(ExpenseReducer.rootReducer, composeWithDevTools(applyMiddleware()))
}
