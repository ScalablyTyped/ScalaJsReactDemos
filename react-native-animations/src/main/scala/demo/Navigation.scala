package demo

import japgolly.scalajs.react.component.{Js, JsFn}
import japgolly.scalajs.react.{Children, CtorType, JsComponent, JsFnComponent, ScalaFnComponent}
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.raw.React.ComponentClassP
import japgolly.scalajs.react.vdom.all.TagMod
import japgolly.scalajs.react.vdom.html_<^._
import typings.react.mod.{ComponentProps, ComponentType}
import typings.reactNavigationCore.reactNavigationCoreStrings
import typings.reactNavigationCore.typesMod.{DefaultNavigatorOptions, TypedNavigator}
import typings.reactNavigationDrawer.createDrawerNavigatorMod.Props
import typings.reactNavigationDrawer.mod.createDrawerNavigator
import typings.reactNavigationDrawer.typesMod.{DrawerNavigationEventMap, DrawerNavigationOptions}
import typings.reactNavigationNative.components.NavigationContainer
import typings.reactNavigationRouters.drawerRouterMod.DrawerNavigationState
import typings.std.{Omit, Record}

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, |}

object Navigation {

  val Drawer = createDrawerNavigator[Record[java.lang.String, js.UndefOr[js.Object]]]()

  type NavigatorProps = Omit[ComponentProps[js.Function1[Props, React.Element]], reactNavigationCoreStrings.initialRouteName | reactNavigationCoreStrings.children | reactNavigationCoreStrings.screenOptions] with DefaultNavigatorOptions[DrawerNavigationOptions, Record[String, UndefOr[js.Object]]]
  private val navigator: ComponentType[NavigatorProps] = Drawer.Navigator

  private val navigatorComponent: JsFn.Component[NavigatorProps, CtorType.PropsAndChildren] = JsFnComponent[NavigatorProps, Children.Varargs](navigator.asInstanceOf[ComponentClassP[NavigatorProps]])


  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>


//    <NavigationContainer>
//      <Drawer.Navigator initialRouteName="Home">
//        <Drawer.Screen name="Home" component={HomeScreen} />
//        <Drawer.Screen name="Notifications" component={NotificationsScreen} />
//      </Drawer.Navigator>
//    </NavigationContainer>
  NavigationContainer.apply(
    navigatorComponent(js.Dynamic.literal(initialRouteName = "Home").asInstanceOf[NavigatorProps])()
    // TODO :(
  )
    <.div()
  }


}
