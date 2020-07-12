package hello.world

import japgolly.scalajs.react.{CallbackTo, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^._
import typings.reactNative.components.{Text, View}
import typings.reactRouterNative.components._
import typings.reactRouter.mod._

object ReactRouter {

  val component = ScalaFnComponent[`match`[_]] { m =>
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
          .setRender(props => CallbackTo(Topic.component(props.`match`.asInstanceOf[`match`[Topic.Param]]).rawNode))
      ),
      Route(RouteProps().setPath(m.path).setRender(_ => CallbackTo(Text("Please select a topic").rawNode)).setExact(true))
    )
  }
}
