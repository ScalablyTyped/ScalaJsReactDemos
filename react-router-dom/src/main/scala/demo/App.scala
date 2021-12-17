package demo

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom
import typings.reactRouter.mod.{`match`, RouteProps}
import typings.reactRouterDom.components.*

import scala.scalajs.js

def home: VdomElement = <.div(<.h2("Home"))

def about: VdomElement = <.div(<.h2("About"))

val App = ScalaFnComponent[Unit] { _ =>
  val renderIntro = <.div(
    <.header(^.className := "App-header")(<.h1(^.className := "App-title")("Welcome to React (with Scala.js!)")),
    <.p(^.className := "App-intro")("To get started, edit ", <.code("App.scala"), " and save to reload.")
  )

  <.div(^.className := "App")(
    renderIntro,
    BrowserRouter(
      <.div(
        <.ul(
          <.li(Link[js.Object](to = "/")("Home")),
          <.li(Link[js.Object](to = "/about")("About")),
          <.li(Link[js.Object](to = "/topics")("Topics"))
        ),
        <.hr(),
        Route(RouteProps().setExact(true).setPath("/").setRender(_ => home.rawElement)),
        Route(RouteProps().setPath("/about").setRender(_ => about.rawElement)),
        Route(RouteProps().setPath("/topics").setRender(props => Topics(props.`match`).rawElement))
      )
    )
  )
}

val Topics = ScalaFnComponent[`match`[?]] { m =>
  <.div(
    <.h2("Topics"),
    <.ul(
      <.li(Link[js.Object](to = m.url + "/rendering")("Rendering with React")),
      <.li(Link[js.Object](to = m.url + "/components")("Components")),
      <.li(Link[js.Object](to = m.url + "/props-v-state")("Props v. State"))
    ),
    <.hr(),
    Route(
      RouteProps()
        .setPath(m.path + "/:topicId")
        .setRender(props => Topic(props.`match`.asInstanceOf[`match`[Param]]).rawElement)
    ),
    Route(RouteProps().setExact(true).setPath(m.path).setRender(_ => <.h3("Please select a topic").rawElement))
  )
}

@js.native
trait Param extends js.Object:
  val topicId: String = js.native

val Topic = ScalaFnComponent[`match`[Param]] { m =>
  <.div(
    <.h3("Topic: " + m.params.topicId)
  )
}

@main
def main: Unit =
  val container = Option(dom.document.getElementById("root")).getOrElse {
    val elem = dom.document.createElement("div")
    elem.id = "root"
    dom.document.body.appendChild(elem)
    elem
  }

  App().renderIntoDOM(container)
end main
