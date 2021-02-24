package demo

import demo.facade.AnimatedView.AnimatedViewProps
import demo.facade.{AnimatedFlatList, AnimatedText, AnimatedView}
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CtorType, ScalaFnComponent}
import typings.react.mod._
import typings.reactNative.components.{StatusBar, TextInput, TouchableOpacity, View}
import typings.reactNative.mod._
import typings.reactNative.reactNativeStrings

import scala.scalajs.js
import scala.scalajs.js.Object.keys

// https://gist.github.com/catalinmiron/341457332b8962fbc1cbb39f978a3882
object CountdownApp {

  private val scaledSize: ScaledSize = Dimensions.get_window(reactNativeStrings.window);
  private val black = "#323F4E"
  private val red = "#F76A6A"
  private val text = "#ffffff"

  private val timers: js.Array[Int] = keys(js.Array[Int](0 to 13: _*)).map(_.toInt).map(i => if (i == 0) 1 else i * 5)
  private val ITEM_SIZE: ImageRequireSource = scaledSize.width * 0.38
  private val ITEM_SPACING: ImageRequireSource = (scaledSize.width - ITEM_SIZE) / 2

  private val textStyle: TextStyle = TextStyle()
    .setFontSize(ITEM_SIZE * 0.8)
    .setFontFamily("Menlo")
    .setColor(text)
    .setFontWeight(reactNativeStrings.`900`)

  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val scrollX = useRef(new Animated.Value(0)).current.asInstanceOf[Animated.Value]
    val js.Tuple2(duration, setDuration) = useState[Int](timers(0))
    val inputRef = useRef[TextInput]()
    val timerAnimation = useRef(new Animated.Value(scaledSize.height)).current.asInstanceOf[Animated.Value]
    val textInputAnimation = useRef(new Animated.Value(timers(0))).current.asInstanceOf[Animated.Value]
    val buttonAnimation = useRef(new Animated.Value(0)).current.asInstanceOf[Animated.Value]

    useEffect((() => {
      val listener = textInputAnimation.addListener { value =>
        //https://stackoverflow.com/questions/57876152/how-to-use-the-useref-hook-for-setnativeprops-in-react-native
        if (inputRef.current != null)
          inputRef.current
            .asInstanceOf[js.Dynamic]
            .setNativeProps(
              js.Dynamic.literal(
                text = Math.ceil(value.value).toString
              )
            )
      }
      (() => {
        textInputAnimation.removeListener(listener)
        textInputAnimation.removeAllListeners()
      }): Destructor
    }): EffectCallback)

    val animation = useCallback(
      () => {
        textInputAnimation.setValue(duration)
        Animated
          .sequence(
            js.Array[Animated.CompositeAnimation](
              Animated.timing(
                buttonAnimation,
                Animated
                  .TimingAnimationConfig(toValue = 1, useNativeDriver = true)
                  .setDuration(300)
              ),
              Animated.timing(
                timerAnimation,
                Animated
                  .TimingAnimationConfig(toValue = 0, useNativeDriver = true)
                  .setDuration(300)
              ),
              Animated.parallel(
                js.Array(
                  Animated.timing(
                    textInputAnimation,
                    Animated
                      .TimingAnimationConfig(toValue = 0, useNativeDriver = true)
                      .setDuration(duration * 1000)
                  ),
                  Animated.timing(
                    timerAnimation,
                    Animated
                      .TimingAnimationConfig(toValue = scaledSize.height, useNativeDriver = true)
                      .setDuration(duration * 1000)
                  )
                )
              ),
              Animated.delay(400)
            )
          )
          .start(
            (
                (_: Animated.EndResult) => {
                  Vibration.cancel()
                  Vibration.vibrate()
                  textInputAnimation.setValue(duration)
                  Animated
                    .timing(
                      buttonAnimation,
                      Animated
                        .TimingAnimationConfig(toValue = 0, useNativeDriver = true)
                        .setDuration(300)
                    )
                    .start()
                }
            ): Animated.EndCallback
          )

      },
      js.Array(duration)
    )

    val opacity = buttonAnimation
      .interpolate(Animated.InterpolationConfigType(inputRange = js.Array(0, 1), outputRange = js.Array(1, 0)))

    val translateY = buttonAnimation
      .interpolate(Animated.InterpolationConfigType(inputRange = js.Array(0, 1), outputRange = js.Array(0, 200)))

    val textOpacity = buttonAnimation
      .interpolate(Animated.InterpolationConfigType(inputRange = js.Array(0, 1), outputRange = js.Array(0, 1)))

    View.style(
      ViewStyle()
        .setFlex(1)
        .setBackgroundColor(black)
    )(
      StatusBar.hidden(true),
      AnimatedView.component(
        js.Dynamic
          .literal(
            style = js.Array(
              js.Dynamic.literal(
                backgroundColor = red,
                height = scaledSize.height,
                width = scaledSize.width,
                transform = js.Array(
                  js.Dynamic.literal(
                    translateY = timerAnimation
                  )
                )
              ),
              StyleSheet.absoluteFillObject
            )
          )
          .asInstanceOf[AnimatedViewProps]
      )(),
      AnimatedView.component(
        js.Dynamic
          .literal(
            style = js.Array(
              js.Dynamic.literal(
                justifyContent = reactNativeStrings.`flex-end`,
                alignItems = reactNativeStrings.center,
                paddingBottom = 100,
                transform = js.Array(
                  js.Dynamic.literal(
                    translateY = translateY
                  )
                ),
                opacity = opacity
              ),
              StyleSheet.absoluteFillObject
            )
          )
          .asInstanceOf[AnimatedViewProps]
      )(
        TouchableOpacity
          .onPress(_ => Callback(animation()))(
            View.style(
              ViewStyle()
                .setWidth(80)
                .setHeight(80)
                .setBorderRadius(80)
                .setBackgroundColor(red)
            )
          )
      ),
      View.style(
        ViewStyle()
          .setPosition(reactNativeStrings.absolute)
          .setTop(scaledSize.height / 3)
          .setLeft(0)
          .setRight(0)
          .setFlex(1)
      )(
        AnimatedView.component(
          js.Dynamic
            .literal(
              position = reactNativeStrings.absolute,
              width = ITEM_SIZE,
              justifyContent = reactNativeStrings.center,
              alignSelf = reactNativeStrings.center,
              alignItems = reactNativeStrings.center,
              opacity = textOpacity
            )
            .asInstanceOf[AnimatedViewProps]
        )(
          TextInput
          // TODO :(
            .withRef(ref => inputRef.current = ref)
            .style(textStyle)
            .defaultValue(duration.toString)()
        ),
        AnimatedFlatList()
          .data(timers)
          .keyExtractor((item, _) => item.toString)
          .horizontal(true)
          .bounces(false)
          .set(
            "onScroll",
            Animated.event(
              js.Array(
                js.Dynamic
                  .literal(
                    nativeEvent = js.Dynamic.literal(
                      contentOffset = js.Dynamic.literal(
                        x = scrollX
                      )
                    )
                  )
                  .asInstanceOf[Animated.Mapping]
              ),
              Animated.EventConfig(true)
            )
          )
          // https://github.com/necolas/react-native-web/issues/1021
          .onMomentumScrollEnd(ev =>
            Callback {
              val index: Int =
                Math.round(ev.nativeEvent.asInstanceOf[js.Dynamic].contentOffset.x.asInstanceOf[Int] / ITEM_SIZE).toInt
              setDuration(timers(index))
            }
          )
          .showsHorizontalScrollIndicator(false)
          .snapToInterval(ITEM_SIZE)
          .decelerationRate(reactNativeStrings.fast)
          .style(ViewStyle().setFlexGrow(0).set("opacity", opacity))
          .contentContainerStyle(ViewStyle().setPaddingHorizontal(ITEM_SPACING))
          .renderItem { renderItem =>
            val inputRange = js.Array(
              (renderItem.index - 1) * ITEM_SIZE,
              renderItem.index * ITEM_SIZE,
              (renderItem.index + 1) * ITEM_SIZE
            )
            val opacity: Animated.AnimatedInterpolation =
              scrollX.interpolate(Animated.InterpolationConfigType(inputRange, js.Array(.4, 1, .4)))

            val scale: Animated.AnimatedInterpolation =
              scrollX.interpolate(Animated.InterpolationConfigType(inputRange, js.Array(.7, 1, .7)))

            View
              .style(
                ViewStyle()
                  .setWidth(ITEM_SIZE)
                  .setJustifyContent(reactNativeStrings.center)
                  .setAlignItems(FlexAlignType.center)
              )(
                AnimatedText().style(
                  textStyle
                    .set("opacity", opacity)
                    .set("transform", js.Array(js.Dynamic.literal(scale = scale)))
                )(renderItem.item)
              )
              .build
              .rawElement
          }
      )
    )
  }

}
