package demo

import demo.facade.AnimatedView
import demo.facade.AnimatedView.AnimatedProps
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.{CallbackTo, CtorType, ScalaFnComponent}
import typings.react.mod.useState
import typings.reactNative.components.{TouchableWithoutFeedback, View}
import typings.reactNative.mod.Animated.TimingAnimationConfig
import typings.reactNative.mod.{Animated, FlexAlignType, ViewStyle}
import typings.reactNative.reactNativeStrings

import scala.scalajs.js

object AnimatedIconEasyApp {

  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val js.Tuple2(activated, setActivated) = useState(false)
    val js.Tuple2(upperAnimation, _) = useState(new Animated.Value(0))
    val js.Tuple2(lowerAnimation, _) = useState(new Animated.Value(0))

    val startAnimation = () => {
      setActivated(!activated)

      Animated
        .timing(
          upperAnimation,
          TimingAnimationConfig(
            if (activated) 0 else 25,
            true
          ).setDuration(300)
        )
        .start()

      Animated
        .timing(
          lowerAnimation,
          TimingAnimationConfig(
            if (activated) 0 else -25,
            true
          ).setDuration(300)
        )
        .start()
    }

    View.style(
      ViewStyle()
        .setFlex(1)
        .setBackgroundColor("#fff")
        .setAlignItems(FlexAlignType.center)
        .setJustifyContent(reactNativeStrings.center)
    )(
      TouchableWithoutFeedback().onPress((_) => CallbackTo[Unit](startAnimation()))(
        View.style(
          ViewStyle()
            .setDisplay(reactNativeStrings.flex)
            .setFlexDirection(reactNativeStrings.column)
            .setJustifyContent(reactNativeStrings.`space-between`)
            .setAlignItems(reactNativeStrings.center)
            .setWidth(100)
            .setHeight(100)
            .setPaddingVertical(20)
            .setBorderRadius(50)
            .setBackgroundColor("green")
        )(
          AnimatedView.component(
            js.Dynamic
              .literal(
                style = js.Array(
                  js.Dynamic.literal(
                    width = "60%",
                    height = 10,
                    borderRadius = 10,
                    backgroundColor = "black",
                    transform = js.Array(js.Dynamic.literal(translateY = upperAnimation))
                  )
                )
              )
              .asInstanceOf[AnimatedProps]
          )(),
          AnimatedView.component(
            js.Dynamic
              .literal(
                style = js.Array(
                  js.Dynamic.literal(
                    width = "60%",
                    height = 10,
                    borderRadius = 10,
                    backgroundColor = "black"
                  )
                )
              )
              .asInstanceOf[AnimatedProps]
          )(),
          AnimatedView.component(
            js.Dynamic
              .literal(
                style = js.Array(
                  js.Dynamic.literal(
                    width = "60%",
                    height = 10,
                    borderRadius = 10,
                    backgroundColor = "black",
                    transform = js.Array(js.Dynamic.literal(translateY = lowerAnimation))
                  )
                )
              )
              .asInstanceOf[AnimatedProps]
          )()
        )
      )
    )
  }

}
