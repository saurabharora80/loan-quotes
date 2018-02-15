
object Executable extends App {

  if (args.length < 2) {
    println("Please provide the filename and required loan amount")
  } else {
    println(GenerateQuote(args(0), args(1)))
  }
}
