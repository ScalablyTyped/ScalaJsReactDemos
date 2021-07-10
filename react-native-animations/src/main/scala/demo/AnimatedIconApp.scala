package demo

import demo.facade.AnimatedView
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.{CallbackTo, CtorType, ScalaFnComponent}
import typings.react.mod.useState
import typings.reactNative.components.{TouchableWithoutFeedback, View}
import typings.reactNative.mod.Animated.{InterpolationConfigType, SpringAnimationConfig, TimingAnimationConfig}
import typings.reactNative.mod._
import typings.reactNative.reactNativeStrings

import scala.scalajs.js

// https://cheesecakelabs.com/blog/first-steps-with-react-native-animations/
object AnimatedIconApp {

  private def commonStyle: ViewStyle = ViewStyle()
    .setWidth("60%")
    .setBorderRadius(10)
    .setBackgroundColor("black")

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
          .asInstanceOf[TranslateYTransform]
      ),
      js.Dynamic.literal(
        rotate = rotation
          .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array("0deg", "45deg")))
          .asInstanceOf[RotateTransform]
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
          ViewProps()
            .setStyle(
              ViewStyle()
                .setDisplay(reactNativeStrings.flex)
                .setFlexDirection(reactNativeStrings.column)
                .setJustifyContent(reactNativeStrings.`space-between`)
                .setAlignItems(reactNativeStrings.center)
                .setWidth(100)
                .setHeight(100)
                .setPaddingVertical(20)
                .setBorderRadius(50)
                .setBackgroundColor(jsAnimation
                  .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array("green", "red")))
                  .asInstanceOf[OpaqueColorValue])
            )
        )(
          AnimatedView.component(
            ViewProps().setStyle(commonStyle.setHeight(10).set("transform", upper))
          )(),
          AnimatedView.component(
            ViewProps().setStyle(commonStyle.set("height", middle))
          )(),
          AnimatedView.component(
            ViewProps().setStyle(commonStyle.setHeight(10).set("transform", lower))
          )()
        )
      )
    )
  }

}
