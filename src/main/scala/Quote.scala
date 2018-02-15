import scala.math.BigDecimal.RoundingMode
import scala.math.pow

object Quote {
  private val Total_No_Of_Payments = 36

  private object MonthlyPayment {
    private val Payments_Per_Year = 12.0

    def apply(requiredAmount: LoanAmount, annualInterestRate: Double, noOfPayments: Int): Double = {
      val periodicInterestRate = annualInterestRate / (Payments_Per_Year * 100.0)
      val discountFactor = (pow(1 + periodicInterestRate, noOfPayments) - 1)/(periodicInterestRate * pow(1 + periodicInterestRate, noOfPayments))
      BigDecimal(requiredAmount.amount / discountFactor).setScale(2, RoundingMode.UP).toDouble
    }
  }

  def apply(requiredAmount: LoanAmount, annualInterestRate: Double): Quote = {
    val monthlyPayment = MonthlyPayment(requiredAmount, annualInterestRate, Total_No_Of_Payments)
    new Quote(requiredAmount, annualInterestRate, monthlyPayment, BigDecimal(Total_No_Of_Payments * monthlyPayment).setScale(2, RoundingMode.UP).toDouble)
  }
}

case class Quote(requiredAmount: LoanAmount, rate: Double, monthlyRepayment: Double, totalRepayment: Double) {
  override def toString: String = {
    s"""Requested amount: £$requiredAmount
       |Rate: $rate%
       |Monthly repayment: £$monthlyRepayment
       |Total repayment: £$totalRepayment""".stripMargin
  }
}