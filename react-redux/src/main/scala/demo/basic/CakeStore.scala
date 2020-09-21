package demo.basic

import typings.redux.mod.{Store, createStore}
import typings.reduxDevtoolsExtension.developmentOnlyMod.devToolsEnhancer
import typings.reduxDevtoolsExtension.mod.EnhancerOptions

import scala.scalajs.js

object CakeStore {

  val Store: Store[CakeReducer.State with js.Object, CakeActions.CakeAction] with Any =
    createStore(CakeReducer.Reducer, devToolsEnhancer(EnhancerOptions().setName("cake store")))

}
