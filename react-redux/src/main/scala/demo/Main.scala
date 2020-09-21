package demo

import demo.basic.CakeActions.CakeAction
import demo.basic.{CakeContainer, CakeStore}
import org.scalajs.dom
import typings.reactRedux.components.Provider

//
// https://www.youtube.com/watch?v=gFZiQnM3Is4&list=PLC3y8-rFHvwheJHvseC3I0HuYI2f46oAK&index=18
object Main {

  def main(args: Array[String]): Unit = {

    // TODO ExternalComponent of slinky equivalent in scaljs-react :(
    //val ConnectedDemo: ExternalComponent { type Props = Demo.Props } =
    //  ReduxFacade.simpleConnect(Store, Demo.component)

    Provider[CakeAction](CakeStore.Store)(
      CakeContainer.component(
        CakeContainer.Props()
      )
    ).renderIntoDOM(dom.document.getElementById("container"))
  }
}
