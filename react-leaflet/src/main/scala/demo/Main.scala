package demo

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("./node_modules/leaflet/dist/leaflet.css", JSImport.Namespace)
@js.native
object Css extends js.Object

@main
def main: Unit =
  /* touch to load */
  typings.leaflet.leafletRequire
  Css

  App().renderIntoDOM(dom.document.getElementById("container"))
