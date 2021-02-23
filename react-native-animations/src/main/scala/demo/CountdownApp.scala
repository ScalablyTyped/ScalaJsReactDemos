package demo

import demo.facade.AnimatedView
import demo.facade.AnimatedView.AnimatedProps
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CtorType, ScalaFnComponent, _}
import typings.react.mod.{useRef, useState}
import typings.reactNative.components.{FlatList, StatusBar, Text, TextInput, TouchableOpacity, View}
import typings.reactNative.mod.Animated.{AnimatedInterpolation, InterpolationConfigType}
import typings.reactNative.mod._
import typings.reactNative.mod.global.console
import typings.reactNative.reactNativeStrings

import scala.scalajs.js
import scala.scalajs.js.Object.keys
import scala.scalajs.js.annotation.JSImport

// https://gist.github.com/catalinmiron/341457332b8962fbc1cbb39f978a3882
object CountdownApp {

  val scaledSize: ScaledSize = Dimensions.get_window(reactNativeStrings.window);
  val black = "#323F4E"
  val red = "#F76A6A"
  val text = "#ffffff"

  val timers = keys(js.Array[Int](0 to 13: _*)).map(_.toInt).map(i => if (i == 0) 1 else i * 5)
  val ITEM_SIZE = scaledSize.width * 0.38
  val ITEM_SPACING = (scaledSize.width - ITEM_SIZE) / 2

  val textStyle = TextStyle()
    .setFontSize(ITEM_SIZE * 0.8)
    .setFontFamily("Menlo")
    .setColor(text)
    .setFontWeight(reactNativeStrings.`900`)

  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val scrollX = useRef(new Animated.Value(0)).current.asInstanceOf[Animated.Value]
    val js.Tuple2(duration, setDuration) = useState[Int](timers(0))
    val inputRef = useRef()
    val timerAnimation = useRef(new Animated.Value(scaledSize.height)).current.asInstanceOf[Animated.Value]
    val textInputAnimation = useRef(new Animated.Value(timers(0))).current.asInstanceOf[Animated.Value]
    val buttonAnimation = useRef(new Animated.Value(0)).current.asInstanceOf[Animated.Value]

    val buttonOpacity = buttonAnimation
      .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(1, 0)))

    val translateY = buttonAnimation
      .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(0, 200)))
    val textOpacity = buttonAnimation
      .interpolate(InterpolationConfigType(js.Array(0, 1), js.Array(0, 1)))

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
          .asInstanceOf[AnimatedProps]
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
                )
              ),
              buttonOpacity,
              StyleSheet.absoluteFillObject
            )
          )
          .asInstanceOf[AnimatedProps]
      )(
        TouchableOpacity
          .onPress(_ => Callback())(
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
              alignItems = reactNativeStrings.center
              //opacity = textOpacity
            )
            .asInstanceOf[AnimatedProps]
        )(
          TextInput
            .withRef(_ => inputRef)
            .style(textStyle)
            .defaultValue(duration.toString)()
        ),
        FlatList()
          .data(timers)
          .keyExtractor((item, _) => item.toString)
          .horizontal(true)
          .bounces(false)
          // TODO
          //   onScroll={Animated.event(
          //    [{ nativeEvent: { contentOffset: { x: scrollX } } }],
          //    {
          //      useNativeDriver: false,
          //    }
          //  )}
          .onScroll(_ =>
            CallbackTo(
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
                Animated.EventConfig(false)
              )
            )
          )
          .onMomentumScrollEnd(ev =>
            CallbackTo {
              val index =
                Math.round(ev.nativeEvent.asInstanceOf[js.Dynamic].contentOffset.x.asInstanceOf[Double] / ITEM_SIZE)
              console.log(s"index $index")
              setDuration(timers(index.toInt))
            }
          )
          .showsHorizontalScrollIndicator(false)
          .snapToInterval(ITEM_SIZE)
          .decelerationRate(reactNativeStrings.fast)
          //.set("style", js.Dynamic.literal(flexGrow = 0, opacity = textOpacity))
          .style(ViewStyle().setFlexGrow(0))
          .contentContainerStyle(ViewStyle().setPaddingHorizontal(ITEM_SPACING))
          .renderItem { renderItem =>
            val inputRange = js.Array(
              (renderItem.index - 1) * ITEM_SIZE,
              renderItem.index * ITEM_SIZE,
              (renderItem.index + 1) * ITEM_SIZE
            )
            val opacity: AnimatedInterpolation =
              scrollX.interpolate(InterpolationConfigType(inputRange, js.Array(.4, 1, .4)))

            val scale: AnimatedInterpolation =
              scrollX.interpolate(InterpolationConfigType(inputRange, js.Array(.7, 1, .7)))

            View
              .style(
                ViewStyle()
                  .setWidth(ITEM_SIZE)
                  .setJustifyContent(reactNativeStrings.center)
                  .setAlignItems(FlexAlignType.center)
              )(
                Text.withProps(
                  js.Dynamic
                    .literal(
                      style = js.Array(
                        js.Dynamic.literal(
                          fontSize = ITEM_SIZE * 0.8,
                          fontFamily = "Menlo",
                          color = text,
                          fontWeight = reactNativeStrings.`900`,
                          opacity = opacity
                          //transform = js.Array(scale)
                        )
                      )
                    )
                    .asInstanceOf[TextProps]
                  //textStyle
                  // TODO opacity
                  //.set("opacity", opacity)
                  // TODO transform
                  //.set("transform", scale)
                )(renderItem.item)
              )
              .build
              .rawElement
          }
      )
    )

  }

}
