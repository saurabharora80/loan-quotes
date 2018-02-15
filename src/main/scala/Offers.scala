import scala.util.Try

case class Offer(lender: String, annualInterestRate: Double, maxLoanAmount: Int)

object Offers {
  def apply(offers: Seq[String]): Seq[Offer] = {
    offers.filterNot(_.isEmpty).map { offerData =>
      val offerElements = offerData.split(",")
      val lendersName = Try(offerElements(0).trim).getOrElse(throw InvalidOfferException(offerData))
      val interestRateInPercent = Try(offerElements(1).trim.toDouble * 100).getOrElse(throw InvalidOfferException(offerData))
      val maximumAvailableLoan = Try(offerElements(2).trim.toInt * 100).getOrElse(throw InvalidOfferException(offerData))

      Offer(lendersName, interestRateInPercent, maximumAvailableLoan)
    }
  }
}

case class InvalidOfferException(offerData: String) extends RuntimeException(s"Invalid Offer: $offerData")