case class LoanAmount(amount: Int) {

  private val MaxLoanAmount = 15000
  private val MinimumLoanAmount = 1000

  def lessThan(maxLoanAmount: Int): Boolean = amount <= maxLoanAmount

  require(amount >= MinimumLoanAmount && amount <= MaxLoanAmount && amount % 100 == 0,
    s"Please provide a loan amount, in increments of 100, between 1000 and 15000; instead of $amount")

  override def toString: String = amount.toString
}
