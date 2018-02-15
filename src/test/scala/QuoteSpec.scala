import org.scalatest.{Matchers, WordSpec}

class QuoteSpec extends WordSpec with Matchers {

  "quote" should {
    "contain monthly and total repayment" in {
      Quote(LoanAmount(2000), 7.4) shouldBe new Quote(LoanAmount(2000), 7.4, 62.13, 2236.69)
      Quote(LoanAmount(3000), 5.3) shouldBe new Quote(LoanAmount(3000), 5.3, 90.32, 3251.52)
      Quote(LoanAmount(5000), 3.4) shouldBe new Quote(LoanAmount(5000), 3.4, 146.29, 5266.44)
      Quote(LoanAmount(10000), 2.9) shouldBe new Quote(LoanAmount(10000), 2.9, 290.38, 10453.68)
      Quote(LoanAmount(15000), 2.9) shouldBe new Quote(LoanAmount(15000), 2.9, 435.56, 15680.16)
    }

    "provide a its formatted string representation" in {
      Quote(LoanAmount(2000), 7.4).toString shouldBe
        s"""Requested amount: £2000
           |Rate: 7.4%
           |Monthly repayment: £62.13
           |Total repayment: £2236.69""".stripMargin
    }
  }

}
