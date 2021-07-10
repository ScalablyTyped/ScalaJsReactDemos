package demo

import org.scalajs.dom.document
import typings.nivoLine.mod.Serie
import typings.std.global.console

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native @JSImport("./data.json", JSImport.Namespace)
val Data: js.Array[Serie] = js.native

@main
def main: Unit =
  console.warn(Data)
  App.component(Data).renderIntoDOM(document.getElementById("container"))
