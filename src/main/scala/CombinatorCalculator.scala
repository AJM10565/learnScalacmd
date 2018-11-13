package edu.luc.cs.laufer.cs473.expressions

import org.jline.reader.LineReaderBuilder
import org.jline.terminal.TerminalBuilder

import scala.util.{Failure, Success, Try}

object CombinatorCalculator extends App {

  val terminal = TerminalBuilder.terminal
  val reader = LineReaderBuilder.builder.terminal(terminal).build
  val prompt = "Enter infix expression: "

  var store = behaviors.newstore
  def processExpr(input: String): Unit = {
    println("You entered: " + input)
    val result = CombinatorParser.parseAll(CombinatorParser.toplevel, input)
    if (result.isEmpty) {
      println("This expression could not be parsed")
    } else {
      import behaviors._
      val expr = result.get
      println("The parsed statements are: ")
      println(toFormattedString(expr)(false))
      println("The unparsed statements are:")
      println(toFormattedString(expr)(true))

      // println("It has size " + size(expr) + " and height " + height(expr)) // Still Doesn't work
      println("Memory: " + store)
      println("It evaluates to " + Execute(store)(expr))
      println("Memory: " + store)
    }
  }
  // needs a try catch
  if (args.length > 0) {
    processExpr(args mkString " ")
  } else {
    var x = true
    while (x) {

      try {
        var in = reader.readLine(prompt)
        processExpr(in)
      } catch {
        case _: Throwable => x = false
      }

    }

  }

  //  // needs a try catch
  //  if (args.length > 0) {
  //    processExpr(args mkString " ")
  //  } else {
  //    while (true) {
  //      print("Enter infix expression: ")
  //      var in = reader.readLine(prompt)
  //      processExpr(in)
  //
  //    }
  //
  //  }
}
