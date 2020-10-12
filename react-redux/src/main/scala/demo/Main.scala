package demo

import demo.basic.CakeActions.CakeAction
import demo.basic.CakeStore.Store
import demo.basic.{CakeContainer, CakeStore}
import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.component.Js.Component
import org.scalajs.dom
import typings.reactRedux.components.Provider

import scala.scalajs.js

//
// https://www.youtube.com/watch?v=gFZiQnM3Is4&list=PLC3y8-rFHvwheJHvseC3I0HuYI2f46oAK&index=18
object Main {

  def main(args: Array[String]): Unit = {

    val ConnectedDemo: Component[CakeContainer.Props, Null, CtorType.PropsAndChildren] =
      ReduxFacade.simpleConnect(Store, CakeContainer.component)

    Provider[CakeAction](CakeStore.Store)(
      ConnectedDemo {
        val props = (new js.Object).asInstanceOf[CakeContainer.Props]
        props
      }()
    ).renderIntoDOM(dom.document.getElementById("container"))
  }
}
