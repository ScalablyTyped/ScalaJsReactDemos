package demo

import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import typings.redux.mod.{Store, createStore}
import typings.reduxDevtoolsExtension.developmentOnlyMod.devToolsEnhancer
import typings.reduxDevtoolsExtension.mod.EnhancerOptions

import scala.scalajs.js

object Main {
  def main(args: Array[String]): Unit = {
    val Store: Store[GithubSearch.State with js.Object, GithubSearch.SearchAction] with Any =
      createStore(GithubSearch.Reducer, devToolsEnhancer(EnhancerOptions().setName("github search store")))
    <.h1("react redux").renderIntoDOM(dom.document.getElementById("container"))
  }
}
