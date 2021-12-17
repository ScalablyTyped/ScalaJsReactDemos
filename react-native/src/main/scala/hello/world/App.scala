package hello.world

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, CtorType, ReactEventFrom, ScalaFnComponent}
import org.scalajs.dom.Element
import typings.antDesignReactNative.anon.PartialLocale
import typings.antDesignReactNative.antDesignReactNativeStrings.xl
import typings.antDesignReactNative.components.{List as AntdList, *}
import typings.bang88ReactNativeDrawerLayout.mod.DrawerLayout
import typings.react.mod.useState
import typings.reactNative.components.ScrollView
import typings.reactNative.mod.NodeHandle
import typings.reactRouter.components.Route
import typings.reactRouter.mod.RouteProps
import typings.reactRouterNative.components.{NativeRouter, Redirect}

import scala.scalajs.js
import scala.scalajs.js.|

sealed abstract class RoutePath(val path: String, val title: String)

object RoutePath:
  object Home extends RoutePath("/", "Home")
  object Antd extends RoutePath("/antd", "Antd")
  object ReactRouter extends RoutePath("/react_router", "React Router")

  val allOrdered: List[RoutePath] = List(Home, Antd, ReactRouter)

def toOption[T](ot: T | Null): Option[T] =
  Option(ot.asInstanceOf[T])

val App: Component[Unit, CtorType.Nullary] = ScalaFnComponent { case () =>
  var ref: Option[DrawerLayout] = None

  val js.Tuple2(redirPath, updateRedirPath) = useState(RoutePath.Home.path)

  def navigateTo(route: RoutePath)(e: ReactEventFrom[NodeHandle with Element]) =
    Callback {
      updateRedirPath(route.path)
      ref.foreach(_.closeDrawer())
    }

  def checkRedirection(stayPath: String, elem: VdomElement): VdomNode =
    if redirPath != stayPath then Redirect(to = redirPath) else elem

  val routeLinks: Seq[VdomElement] = RoutePath.allOrdered.zipWithIndex.map { case (route, index) =>
    ListItem.onPress(navigateTo(route))(Text(route.title)).withKey(index.toString)
  }

  Provider.locale(PartialLocale().setLocale("enUS"))(
    NativeRouter(
      Drawer
        .drawerRef(nullableRef => Callback { ref = toOption(nullableRef) })
        .sidebar(ScrollView(WhiteSpace.size(xl), AntdList(routeLinks*)))(
          AntdList.renderHeaderVdomElement(WhiteSpace.size(xl))(
            ListItem
              .extra(Icon(name = "menu"))
              .onPress(_ => Callback(ref.foreach(_.openDrawer())))("React Native demo")
          )
        ),
      Route(
        RouteProps()
          .setPath(RoutePath.Home.path)
          .setRender(props => checkRedirection(props.`match`.path, Home()).rawNode)
          .setExact(true)
      ),
      Route(
        RouteProps()
          .setPath(RoutePath.Antd.path)
          .setRender(props => checkRedirection(props.`match`.path, Antd()).rawNode)
      ),
      Route(
        RouteProps()
          .setPath(RoutePath.ReactRouter.path)
          .setRender(props => checkRedirection(props.`match`.path, ReactRouter(props.`match`)).rawNode)
      )
    )
  )
}
