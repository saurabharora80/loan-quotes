import scala.io.Source
import scala.util.{Failure, Success, Try}

object GenerateQuote {

  def apply(offersFile: String, requiredLoanAmount: String): String = {
    Try {
      val amount = Try(LoanAmount(requiredLoanAmount.toInt)).getOrElse(throw new RuntimeException("Invalid loan amount"))
      val offers = Offers(Source.fromFile(offersFile).getLines().drop(1).toSeq)
      Quote(amount, ApplicableInterestRate(offers, amount))
    } match {
      case Success(quote) => quote.toString
      case Failure(exception) => exception.getMessage
    }
  }
}


