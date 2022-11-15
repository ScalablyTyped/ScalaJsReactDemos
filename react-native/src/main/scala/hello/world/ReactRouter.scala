package hello.world

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import typings.reactNative.components.{Text, View}
import typings.reactRouter.mod.{Route as _, *}
import typings.reactRouterNative.components.*

val ReactRouter = ScalaFnComponent[`match`[?]] { m =>
  def link(title: String, path: String): VdomElement =
    Link(to = m.url + path).style(Styles.subNavItemStyle)(Text.style(Styles.topicStyle)(title))

  View(
    Text.style(Styles.title)("React Router demo"),
    Text.style(Styles.headerStyle)("Topics"),
    View(
      link("Rendering with React", "/rendering"),
      link("Components", "/components"),
      link("Props v. State", "/props-v-state")
    ),
    Route(
      RouteProps()
        .setPath(m.path + "/:topicId")
        .setRender(props => Topic(props.`match`.asInstanceOf[`match`[Param]]).rawNode)
    ),
    Route(RouteProps().setPath(m.path).setRender(_ => Text("Please select a topic").rawNode))
  )
}
