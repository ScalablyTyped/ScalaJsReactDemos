package hello.world

import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, CallbackTo, ScalaFnComponent}
import typings.antDesignIconsReactNative.antDesignIconsReactNativeStrings.{flag, gift}
import typings.antDesignIconsReactNative.components.{IconFill, IconOutline}
import typings.antDesignReactNative.antDesignReactNativeStrings as antdStrings
import typings.antDesignReactNative.components.{List as AntdList, *}
import typings.antDesignReactNative.mod.Toast
import typings.antDesignReactNative.modalPropsTypeMod.Action
import typings.react.mod.useState
import typings.reactNative.components.ScrollView
import typings.reactNative.mod.{FlexAlignType, ViewStyle}
import typings.reactNative.reactNativeStrings

import scala.scalajs.js

val Antd = ScalaFnComponent[Unit] { case () =>
  val js.Tuple2(isModalVisible, updateIsModalVisible) = useState(false)

  View(
    Text.style(Styles.title)("Antd components"),
    ScrollView
      .automaticallyAdjustContentInsets(false)
      .showsHorizontalScrollIndicator(false)
      .showsVerticalScrollIndicator(false)(
        AntdList.renderHeaderVdomElement(Text("List header"))(
          ListItem
            .arrow(antdStrings.horizontal)
            .onPress(e => Callback(updateIsModalVisible(true)))("Open modal"),
          ListItem
            .arrow(antdStrings.horizontal)
            .onPress(e => Callback(Toast.success("Successful!")))(
              "Launch success toast"
            )
        ),
        View.style(
          ViewStyle()
            .setBackgroundColor("white")
            .setFlex(1)
            .setFlexDirection(reactNativeStrings.column)
            .setJustifyContent(reactNativeStrings.center)
            .setAlignItems(FlexAlignType.center)
        )(
          InputItem.placeholder("input text"),
          InputItem
            .placeholder("password")
            .`type`(antdStrings.password)
            .error(true)
            .onErrorClick(_ => Callback(Toast.fail("Always wrong!")))
            .last(true),
          WingBlank.size(antdStrings.lg)(
            Button
              .onPress(_ => Callback(Toast.fail("Failure!")))
              .`type`(antdStrings.primary)("Launch fail toast")
          ),
          WhiteSpace.size(antdStrings.xl),
          IconFill(flag).size(40),
          IconOutline(name = gift).size(80),
          Icon(name = "experiment").size(antdStrings.lg).color("#A82")
        )
      ),
    Modal(visible = isModalVisible)
      .transparent(true)
      .maskClosable(true)
      .closable(false)
      .title("Basic modal")
      .onClose(Callback(updateIsModalVisible(false)))
      .footer(
        js.Array(
          Action("Cancel").setOnPress(CallbackTo(updateIsModalVisible(false))),
          Action("OK").setOnPress(CallbackTo(updateIsModalVisible(false)))
        )
      )
  )(Text("Some contents..."))
}
