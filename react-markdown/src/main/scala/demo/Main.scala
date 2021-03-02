package demo

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Children, JsComponent}
import org.scalajs.dom
import typings.reactMarkdown.mod.ReactMarkdownProps
import typings.reactSyntaxHighlighter.components.Light
import typings.reactSyntaxHighlighter.{javascriptMod, mod}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

// https://stackblitz.com/edit/react-syntax-highlighter-issue-js
object Main {

  @JSImport("react-markdown", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  val ReactMarkdown = JsComponent[ReactMarkdownProps, Children.None, Null](RawComponent)

  val code = "var a = 5"

  def main(args: Array[String]): Unit = {
    // https://github.com/remarkjs/react-markdown#use-custom-renderers-syntax-highlight
    //import React from 'react'
    //import ReactMarkdown from 'react-markdown'
    //import {Prism as SyntaxHighlighter} from 'react-syntax-highlighter'
    //import {dark} from 'react-syntax-highlighter/dist/esm/styles/prism'
    //import {render} from 'react-dom'
    //
    //const renderers = {
    //  code: ({language, value}) => {
    //    return <SyntaxHighlighter style={dark} language={language} children={value} />
    //  }
    //}
    //
    //// Did you know you can use tildes instead of backticks for code in markdown? ✨
    //const markdown = `Here is some JavaScript code:
    //
    //~~~js
    //console.log('It works!')
    //~~~
    //`
    //
    //render(<ReactMarkdown renderers={renderers} children={markdown} />, document.body)
    //

    //ReactMarkdown(ReactMarkdownPropsBase()
    // TODO
    //.setRenderers()
    //.asInstanceOf[ReactMarkdownProps])(markdown).renderIntoDOM(document.getElementById("app"))
    mod.Light.registerLanguage("javascript", javascriptMod.default)

    Light
      .style(javascriptMod.default)
      .language("javascript")(code)
      .renderIntoDOM(dom.document.getElementById("container"))
  }

}
