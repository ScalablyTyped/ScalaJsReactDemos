package demo.advanced

import demo.advanced.ExpenseReducer
import typings.redux.mod.{applyMiddleware, createStore}
import typings.reduxDevtoolsExtension.developmentOnlyMod.composeWithDevTools
import typings.reduxThunk.mod.default

object ExpenseStore {

  val value =
    createStore(ExpenseReducer.rootReducer, composeWithDevTools(applyMiddleware(default)))

}
