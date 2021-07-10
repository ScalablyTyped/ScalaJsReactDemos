package demo

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.facade.React.{ElementType, Node}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom.raw.XMLHttpRequest
import typings.react.mod.{EffectCallback, useEffect, useState}
import typings.reactMarkdown.components.ReactMarkdown
import typings.reactMarkdown.mod.{ReactMarkdownProps, ReactMarkdownPropsBase}
import typings.reactSyntaxHighlighter.components.{Light => SyntaxHighligther}
import typings.reactSyntaxHighlighter.mod.Light
import typings.reactSyntaxHighlighter.{scalaMod, stylesHljsMod}

import scala.scalajs.js


object DocPage:

  val docFile = "./docs/README.md"

  Light.registerLanguage("scala", scalaMod.default)

  class LanguageValue(val language: String, val value: String) extends js.Object

  val codeRender: js.Function1[LanguageValue, Node] =
    rp => SyntaxHighligther.style(stylesHljsMod.darcula).language(rp.language)(rp.value).build.rawElement

  val component: Component[Unit, CtorType.Nullary] = ScalaFnComponent[Unit] { _ =>
    val js.Tuple2(document, setDocument) = useState[Option[String]](None)

    useEffect((() => {
      val xhr = new XMLHttpRequest
      xhr.onload = _ => {
        setDocument(Some(xhr.responseText))
      }
      xhr.open("GET", docFile)
      xhr.send()
    }): EffectCallback, js.Array(docFile))

    val props = ReactMarkdownPropsBase()
      .setRenderers(StringDictionary("code" -> codeRender).asInstanceOf[StringDictionary[ElementType]])
      .asInstanceOf[ReactMarkdownProps]

    ReactMarkdown(props)(document)

  }
