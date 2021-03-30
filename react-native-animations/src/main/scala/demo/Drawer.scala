package demo

import demo.facade.AnimatedView
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CtorType, ReactEventFrom, ScalaFnComponent}
import org.scalajs.dom.raw.Element
import typings.react.mod.{useCallback, useRef, useState}
import typings.reactNative.anon.Y
import typings.reactNative.components.{StatusBar, Text, TouchableOpacity, View}
import typings.reactNative.mod.Animated.{InterpolationConfigType, SpringAnimationConfig}
import typings.reactNative.mod._
import typings.reactNative.reactNativeStrings

import scala.scalajs.js

object Drawer {

  private val routes = js.Array[String](
    "Get started",
    "Features",
    "Tools",
    "Services",
    "Portfolio",
    "Careers",
    "Contact"
  )

  private val links: js.Array[String] = js.Array[String](
    "Follow us",
    "Quota",
    "Awesome link"
  )

  private val colors = js.Array[String](
    "#69d2e7",
    "#a7dbd8",
    "#e0e4cc",
    "#f38630",
    "#fa6900",
    "#fe4365",
    "#fc9d9a",
    "#f9cdad",
    "#c8c8a9",
    "#83af9b",
    "#ecd078",
    "#d95b43",
    "#c02942",
    "#53777a"
  )

  private val Button
  : Component[js.Tuple3[String, ReactEventFrom[NodeHandle with Element] => Callback, TextStyle], CtorType.Props] =
    ScalaFnComponent[js.Tuple3[String, ReactEventFrom[NodeHandle with Element] => Callback, TextStyle]] {
      case js.Tuple3(title, onPress, style) =>
        TouchableOpacity
          .onPress(onPress)
          .activeOpacity(0.9)(
            Text().style(style)(title)
          )
    }

  private val drawer: Component[js.Tuple3[Animated.ValueXY, ScaledSize, js.Function0[Unit]], CtorType.Props] =
    ScalaFnComponent[js.Tuple3[Animated.ValueXY, ScaledSize, js.Function0[Unit]]] {
      case js.Tuple3(animation, scaledSize, onPress) =>
        val width = scaledSize.width
        val height = scaledSize.height
        val js.Tuple2(selectedRoute, setSelectedRoute) = useState("Get started")
        val opacity = animation.x.interpolate(InterpolationConfigType(js.Array(0, width), js.Array(0, 1)));

        val translateX = animation.x.interpolate(InterpolationConfigType(js.Array(0, width), js.Array(-50, 0)))

        val onRoutePress = useCallback(
          (route: String) => {
            setSelectedRoute(route)
            onPress()
          },
          js.Array()
        )
        View.style(
          ViewStyle()
            .setFlex(1)
            .setBackgroundColor("#222")
            .setPaddingTop(80)
            .setPaddingHorizontal(20)
            .setPaddingBottom(20)
        )(
          // AntDesign
          AnimatedView.component(
            ViewProps().setStyle(
              ViewStyle()
                .setFlex(1)
                .setAlignItems(reactNativeStrings.`flex-start`)
                .setJustifyContent(reactNativeStrings.`space-between`)
              //              .set("opacity", opacity)
              //              .set("transform", js.Array(js.Dynamic.literal(translateX = translateX)))
            )
          )(
            View(
              routes.zipWithIndex.map {
                case (route, index) =>
                  Button.withKey(route)(
                    js.Tuple3(
                      route,
                      _ => Callback(onRoutePress(route)),
                      TextStyle()
                        .setFontSize(32)
                        .setColor(colors(index))
                        .setLineHeight(32 * 1.5)
                        .setTextDecorationLine(
                          if (route == selectedRoute) reactNativeStrings.`line-through` else reactNativeStrings.none
                        )
                    )
                  )
              }.toVdomArray
            ),
            View(
              links.zipWithIndex.map {
                case (link, index) =>
                  Button.withKey(link)(
                    js.Tuple3(
                      link,
                      _ => Callback(onRoutePress(link)),
                      TextStyle()
                        .setFontSize(16)
                        .setMarginBottom(5)
                        .setColor(colors(index + routes.length + 1))
                    )
                  )
              }.toVdomArray
            )
          )
        )
    }


  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val scaledSize: ScaledSize = Dimensions.get_window(reactNativeStrings.window)
    val width = scaledSize.width
    val height = scaledSize.height

    val animation = useRef(new Animated.ValueXY()).current.asInstanceOf[Animated.ValueXY]

    val toCoords = new Animated.ValueXY(Y(width, 0))
    val fromCoords = new Animated.ValueXY(Y(0, height))

    val animate = (toValue: Int) => {
      Animated.spring(
        animation,
        SpringAnimationConfig(
          toValue = if (toValue == 1) toCoords else fromCoords,
          useNativeDriver = true
        ).setSpeed(10).setBounciness(2)
      )
    }
    val opacity = animation.x.interpolate(
      InterpolationConfigType(
        js.Array(0, width),
        js.Array(1, 0)
      )
    )

    val onCloseDrawer: js.Function0[Unit] = useCallback(
      () => {
        StatusBar.barStyle(StatusBarStyle.`dark-content`).hidden(true)
        animate(0).start()
      },
      js.Array()
    )


    View.style(ViewStyle().setFlex(1))(
      drawer(js.Tuple3(animation, scaledSize, onCloseDrawer)),
      StatusBar.hidden(true)
    )

  }

}
