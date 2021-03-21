package demo

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._
import typings.reactRouter.mod.{RouteProps, `match`}
import typings.reactRouterDom.components._

import scala.scalajs.js

object App {

  def home: VdomElement = <.div(<.h2("Home"))

  def about: VdomElement = <.div(<.h2("About"))

  val component = ScalaFnComponent[Unit] { _ =>
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
          Route(RouteProps().setPath("/topics").setRender(props => Topics.component(props.`match`).rawElement))
        )
      )
    )
  }
}

object Topics {

  val component = ScalaFnComponent[`match`[_]] { m =>
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
          .setRender(props => Topic.component(props.`match`.asInstanceOf[`match`[Topic.Param]]).rawElement)
      ),
      Route(RouteProps().setExact(true).setPath(m.path).setRender(_ => <.h3("Please select a topic").rawElement))
    )
  }
}

object Topic {

  @js.native
  trait Param extends js.Object {
    val topicId: String = js.native
  }

  val component = ScalaFnComponent[`match`[Topic.Param]] { m =>
    <.div(
      <.h3("Topic: " + m.params.topicId)
    )
  }
}
