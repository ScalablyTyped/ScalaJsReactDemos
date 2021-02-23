package demo

import demo.facade.AnimatedView
import demo.facade.AnimatedView.AnimatedProps
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.{CallbackTo, Children, CtorType, JsComponent, ScalaFnComponent}
import typings.react.mod.useState
import typings.reactNative.components.{TouchableWithoutFeedback, View}
import typings.reactNative.mod.Animated.{InterpolationConfigType, SpringAnimationConfig, TimingAnimationConfig}
import typings.reactNative.mod.{Animated, FlexAlignType, ViewStyle}
import typings.reactNative.reactNativeStrings

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

// https://cheesecakelabs.com/blog/first-steps-with-react-native-animations/
object AnimatedIconApp {

  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val js.Tuple2(activated, setActivated) = useState(false)
    val js.Tuple2(animation, _) = useState(new Animated.Value(0))
    val js.Tuple2(jsAnimation, _) = useState(new Animated.Value(0))
    val js.Tuple2(rotation, _) = useState(new Animated.Value(0))

    val startAnimation = () => {
      val toValue = if (activated) 0 else 1
      setActivated(!activated)
      Animated
        .parallel(
          js.Array(
            Animated
              .timing(
                animation,
                TimingAnimationConfig(
                  toValue,
                  true
                ).setDuration(300)
              ),
            Animated
              .spring(
                rotation,
                SpringAnimationConfig(
                  toValue,
                  true
                ).setFriction(2)
                  .setTension(140)
              ),
            Animated.timing(
              jsAnimation,
              TimingAnimationConfig(
                toValue,
                false
              ).setDuration(300)
            )
          )
        )
        .start()
    }

    val upper = js.Array(
      js.Dynamic.literal(
        translateY = animation
          .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(0, 25)))
          .asInstanceOf[js.Dynamic]
      ),
      js.Dynamic.literal(
        rotate = rotation
          .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array("0deg", "-45deg")))
          .asInstanceOf[js.Dynamic]
      )
    )

    val middle = jsAnimation
      .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(10, 0)))
      .asInstanceOf[js.Dynamic]

    val lower = js.Array(
      js.Dynamic.literal(
        translateY = animation
          .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(0, -25)))
          .asInstanceOf[js.Dynamic]
      ),
      js.Dynamic.literal(
        rotate = rotation
          .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array("0deg", "45deg")))
          .asInstanceOf[js.Dynamic]
      )
    )

    View.style(
      ViewStyle()
        .setFlex(1)
        .setBackgroundColor("#fff")
        .setAlignItems(FlexAlignType.center)
        .setJustifyContent(reactNativeStrings.center)
    )(
      TouchableWithoutFeedback().onPress((_) => CallbackTo[Unit](startAnimation()))(
        AnimatedView.component(
          js.Dynamic
            .literal(
              style = js.Array(
                js.Dynamic.literal(
                  display = reactNativeStrings.flex,
                  flexDirection = reactNativeStrings.column,
                  justifyContent = reactNativeStrings.`space-between`,
                  alignItems = reactNativeStrings.center,
                  width = 100,
                  height = 100,
                  paddingVertical = 20,
                  borderRadius = 50,
                  backgroundColor = jsAnimation
                    .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array("green", "red")))
                    .asInstanceOf[js.Dynamic]
                )
              )
            )
            .asInstanceOf[AnimatedProps]
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
                    transform = upper
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
                    borderRadius = 10,
                    backgroundColor = "black",
                    height = middle
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
                    transform = lower
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
