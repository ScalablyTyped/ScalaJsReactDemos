package demo.login

import demo.login.Styles.styles
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import org.scalablytyped.runtime.StringDictionary
import typings.classnames.{mod => classNames}
import typings.materialUiCore.anon.{PartialClassNameMapInputC, PartialInputProps}
import typings.materialUiCore.components._
import typings.materialUiCore.materialUiCoreStrings._
import typings.materialUiCore.typographyTypographyMod.Style
import typings.react.components.Fragment
import typings.react.mod.useState
import typings.std.HTMLInputElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("./logo.svg", JSImport.Default)
@js.native
object Logo extends js.Object

@JSImport("./google.svg", JSImport.Default)
@js.native
object GoogleLogo extends js.Object

// https://github.com/flatlogic/react-material-admin/blob/master/src/pages/login/Login.js
object Login {

  val component = ScalaFnComponent[Unit] { _ =>
    val classes = styles(js.undefined)

    val js.Tuple2(isLoading, setIsLoading) = useState(false);
    val js.Tuple2(error, setError) = useState(false);
    val js.Tuple2(activeTabId, setActiveTabId) = useState(0);
    val js.Tuple2(nameValue, setNameValue) = useState("");
    val js.Tuple2(loginValue, setLoginValue) = useState("");
    val js.Tuple2(passwordValue, setPasswordValue) = useState("");

    Grid
      .container(true)
      .className(classes("container"))(
        <.div(^.className := classes("logotypeContainer"))(
          <.img(^.src := Logo.asInstanceOf[String], ^.alt := "logo", ^.className := classes("logotypeImage")),
          Typography.className(classes("logotypeText"))("Material Admin")
        ),
        <.div(^.className := classes("formContainer"))(
          <.div(^.className := classes("form"))(
            Tabs(activeTabId)
              .onChange((_, id) => Callback(setActiveTabId(id.asInstanceOf[Int])))
              .indicatorColor(primary)
              .textColor(primary)
              .centered(true)(
                Tab.label("Login").className(classNames(StringDictionary[js.Any]("root" -> classes("tab")))),
                Tab.label("New User").className(classNames(StringDictionary[js.Any]("root" -> classes("tab"))))
              ),
            activeTabId match {
              case 0 =>
                Fragment(
                  Typography
                    .variant(Style.h1)
                    .className(classes("greeting"))("Good Morning, User"),
                  Button
                    .size(large)
                    .className(classes("googleButton"))(
                      <.img(
                        ^.src := GoogleLogo.asInstanceOf[String],
                        ^.alt := "google",
                        ^.className := classes("googleIcon")
                      ),
                      "Sign in with Google"
                    ),
                  <.div(^.className := classes("formDividerContainer"))(
                    <.div(^.className := classes("formDivider")),
                    Typography.className(classes("formDividerWord"))("or"),
                    <.div(^.className := classes("formDivider"))
                  ),
                  Fade.in(error)(
                    Typography
                      .color(secondary)
                      .className(classes("errorMessage"))("Something is wrong with your login or password :(")
                  ),
                  TextField.StandardTextFieldProps
                    .id("email")
                    .InputProps(
                      PartialInputProps()
                        .setClasses(
                          PartialClassNameMapInputC()
                            .setUnderline(classes("textFieldUnderline"))
                            .setInput(classes("textField"))
                        )
                    )
                    .value(loginValue)
                    .onChange(e => Callback(setLoginValue(e.currentTarget.asInstanceOf[HTMLInputElement].value)))
                    .margin(normal)
                    .placeholder("Email Address")
                    .`type`("email")
                    .fullWidth(true),
                  TextField.StandardTextFieldProps
                    .id("password")
                    .InputProps(
                      PartialInputProps()
                        .setClasses(
                          PartialClassNameMapInputC()
                            .setUnderline(classes("textFieldUnderline"))
                            .setInput(classes("textField"))
                        )
                    )
                    .value(passwordValue)
                    .onChange(e => Callback(setPasswordValue(e.currentTarget.asInstanceOf[HTMLInputElement].value)))
                    .margin(normal)
                    .placeholder("Password")
                    .`type`("password")
                    .fullWidth(true),
                  <.div(^.className := classes("formButtons"))(
                    if isLoading then
                      CircularProgress.size(26).className(classes("loginLoader"))
                    else
                      Button
                        .disabled(loginValue.length == 0 || passwordValue.length == 0)
                        .variant(contained)
                        .color(primary)
                        .size(large)("Login"),
                    Button
                      .color(primary)
                      .size(large)
                      .className(classes("forgetButton"))("Forget Password")
                  )
                )
              case 1 =>
                Fragment(
                  Typography
                    .variant(Style.h1)
                    .className(classes("greeting"))("Welcome"),
                  Typography.variant(Style.h2).className(classes("subGreeting"))("Create your account"),
                  Fade.in(error)(
                    Typography
                      .color(secondary)
                      .className(classes("errorMessage"))("Something is wrong with your login or password :(")
                  ),
                  TextField.StandardTextFieldProps
                    .id("name")
                    .InputProps(
                      PartialInputProps()
                        .setClasses(
                          PartialClassNameMapInputC()
                            .setUnderline(classes("textFieldUnderline"))
                            .setInput(classes("textField"))
                        )
                    )
                    .value(nameValue)
                    .onChange(e => Callback(setNameValue(e.currentTarget.asInstanceOf[HTMLInputElement].value)))
                    .margin(normal)
                    .placeholder("Full Name")
                    .`type`("text")
                    .fullWidth(true),
                  TextField.StandardTextFieldProps
                    .id("email")
                    .InputProps(
                      PartialInputProps()
                        .setClasses(
                          PartialClassNameMapInputC()
                            .setUnderline(classes("textFieldUnderline"))
                            .setInput(classes("textField"))
                        )
                    )
                    .value(loginValue)
                    .onChange(e => Callback(setLoginValue(e.currentTarget.asInstanceOf[HTMLInputElement].value)))
                    .margin(normal)
                    .placeholder("Email Address")
                    .`type`("email")
                    .fullWidth(true),
                  TextField.StandardTextFieldProps
                    .id("password")
                    .InputProps(
                      PartialInputProps()
                        .setClasses(
                          PartialClassNameMapInputC()
                            .setUnderline(classes("textFieldUnderline"))
                            .setInput(classes("textField"))
                        )
                    )
                    .value(passwordValue)
                    .onChange(e => Callback(setPasswordValue(e.currentTarget.asInstanceOf[HTMLInputElement].value)))
                    .margin(normal)
                    .placeholder("Password")
                    .`type`("password")
                    .fullWidth(true),
                  <.div(^.className := classes("formButtons"))(
                    if isLoading then
                      CircularProgress.size(26).className(classes("loginLoader"))
                    else
                      Button
                        .disabled(loginValue.length == 0 || passwordValue.length == 0)
                        .size(large)
                        .variant(contained)
                        .color(primary)
                        .fullWidth(true)
                        .className(classes("createAccountButton"))("Create your account")
                  ),
                  <.div(^.className := classes("formDividerContainer"))(
                    <.div(^.className := classes("formDivider")),
                    Typography.className(classes("formDividerWord"))("or"),
                    <.div(^.className := classes("formDivider"))
                  ),
                  Button
                    .color(primary)
                    .size(large)
                    .className(
                      classNames(
                        StringDictionary[js.Any](
                          classes("googleButton") -> true,
                          classes("googleButtonCreating") -> true
                        )
                      )
                    )(
                      <.img(
                        ^.src := GoogleLogo.asInstanceOf[String],
                        ^.alt := "google",
                        ^.className := classes("googleIcon")
                      ),
                      "Sign in with Google"
                    )
                )
            }
          ),
          Typography
            .color(primary)
            .className(classes("copyright"))("© 2014-2019 Flatlogic, LLC. All rights reserved.")
        )
      )
  }

}
