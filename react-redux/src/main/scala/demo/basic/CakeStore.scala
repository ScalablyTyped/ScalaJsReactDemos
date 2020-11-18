package demo.basic

import typings.redux.mod.{createStore, Store}
import typings.reduxDevtoolsExtension.developmentOnlyMod.devToolsEnhancer
import typings.reduxDevtoolsExtension.mod.EnhancerOptions

object CakeStore {

  val Store: Store[CakeReducer.State, CakeActions.CakeAction] =
    createStore(CakeReducer.Reducer, devToolsEnhancer(EnhancerOptions().setName("cake store")))

}
