package demo

import org.scalajs.dom
import typings.reactSyntaxHighlighter.mod.Light
import typings.reactSyntaxHighlighter.scalaMod

// https://stackblitz.com/edit/react-syntax-highlighter-issue-js
// https://github.com/remarkjs/react-markdown#use-custom-renderers-syntax-highlight
@main
def main: Unit =
  Light.registerLanguage("scala", scalaMod.default)
  DocPage().renderIntoDOM(dom.document.getElementById("container"))
