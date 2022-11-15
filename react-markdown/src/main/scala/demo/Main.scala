package demo

import org.scalajs.dom
import typings.reactSyntaxHighlighter.mod.Light
import typings.reactSyntaxHighlighter.distEsmLanguagesHljsScalaMod.default as scalaLanguage

// https://stackblitz.com/edit/react-syntax-highlighter-issue-js
// https://github.com/remarkjs/react-markdown#use-custom-renderers-syntax-highlight
@main
def main: Unit =
  Light.registerLanguage("scala", scalaLanguage)
  DocPage().renderIntoDOM(dom.document.getElementById("container"))
