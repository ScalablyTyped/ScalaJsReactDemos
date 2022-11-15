package demo

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.facade.React.{ElementType, Node}
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom.XMLHttpRequest
import typings.react.mod.{useEffect, useState, EffectCallback}
import typings.reactMarkdown.components.ReactMarkdown
import typings.reactMarkdown.mod.{ReactMarkdownProps, ReactMarkdownPropsBase}
import typings.reactSyntaxHighlighter.components.Light as SyntaxHighligther
import typings.reactSyntaxHighlighter.mod.Light
import typings.reactSyntaxHighlighter.distEsmStylesHljsMod.darcula
import scala.scalajs.js

val docFile = "./docs/README.md"

class LanguageValue(val language: String, val value: String) extends js.Object

val codeRender: js.Function1[LanguageValue, Node] =
  rp => SyntaxHighligther.style(darcula).language(rp.language)(rp.value).build.rawElement

val DocPage: Component[Unit, CtorType.Nullary] = ScalaFnComponent { case () =>
  val js.Tuple2(document, setDocument) = useState[Option[String]](None)

  useEffect(
    (() =>
      val xhr = new XMLHttpRequest
      xhr.onload = _ => setDocument(Some(xhr.responseText))
      xhr.open("GET", docFile)
      xhr.send()
    ): EffectCallback,
    js.Array(docFile)
  )

  val props = ReactMarkdownPropsBase()
    .setRenderers(StringDictionary("code" -> codeRender).asInstanceOf[StringDictionary[ElementType]])
    .asInstanceOf[ReactMarkdownProps]

  ReactMarkdown(props)(document)
}
