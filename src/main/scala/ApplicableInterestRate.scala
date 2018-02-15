import scala.math.BigDecimal.RoundingMode
import scala.util.Try

object ApplicableInterestRate {
  implicit object OfferOrdering extends Ordering[Offer] {
    override def compare(x: Offer, y: Offer): Int = x.annualInterestRate.compare(y.annualInterestRate)
  }

  def apply(offers: Seq[Offer], requiredAmount: LoanAmount): Double = {
    Try(BigDecimal(offers.filter(o => requiredAmount.lessThan(o.maxLoanAmount)).min.annualInterestRate).setScale(1, RoundingMode.UP).toDouble)
      .getOrElse(throw NoOfferError(requiredAmount))
  }
}

case class NoOfferError(requiredAmount: LoanAmount) extends RuntimeException(s"Unable to find a suitable offer for loan of $requiredAmount")
