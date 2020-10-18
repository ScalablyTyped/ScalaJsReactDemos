package demo.basic

import demo.basic.ReduxFacade.Extractor
import typings.redux.mod.Action

import scala.scalajs.js

object CakeActions {

  @js.native
  sealed trait CakeAction extends Action[String]

  @js.native
  trait BuyCake extends CakeAction

  object BuyCake extends Extractor[BuyCake] {
    protected val _type = "BUY_CAKE"

    @scala.inline
    def apply(): BuyCake = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type.asInstanceOf[js.Any])
      __obj.asInstanceOf[BuyCake]
    }
  }

}
