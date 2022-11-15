package demo

import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import org.scalablytyped.runtime.StringDictionary
import typings.antd.antdStrings
import typings.antd.components.*
import typings.antd.libFormFormMod.useForm
import typings.antd.libGridColMod.ColProps
import typings.rcFieldForm.esInterfaceMod.BaseRule
import typings.std.global.console

object CoordinatedDemo:
  val component = ScalaFnComponent[String] { noteTitle =>
    val form = useForm().head
    Form
      .form(form)
      .labelCol(ColProps().setSpan(5))
      .wrapperCol(ColProps().setSpan(12))
      .onFinish(store => Callback(console.log("Received values of form: ", store)))(
        Form.Item
          .label(noteTitle)
          .name("note")
          .rulesVarargs(BaseRule().setRequired(true).setMessage("Please input your note!"))(
            Input()
          ),
        Form.Item
          .label("Gender")
          .name("gender")
          .rulesVarargs(BaseRule().setRequired(true).setMessage("Please select your gender!'"))(
            Select[String]()
              .placeholder("Select a option and change input text above")
              .onChange { (value, _) =>
                Callback(
                  form.setFieldsValue(
                    StringDictionary(
                      "gender" -> value,
                      "note" -> s"Hi, ${if value == "male" then "man" else "lady"}!"
                    )
                  )
                )
              }(
                Select.Option(value = "male")("Male"),
                Select.Option(value = "female")("Female")
              )
          ),
        Form.Item.wrapperCol(ColProps().setSpan(12).setOffset(5))(
          Button.`type`(antdStrings.primary).htmlType(antdStrings.submit)("Submit")
        )
      )
  }
end CoordinatedDemo
