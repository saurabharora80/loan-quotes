import org.scalatest.{Matchers, WordSpec}

class LoanAmountSpec extends WordSpec with Matchers {

  "user" should {
    Seq(999,900, 0, -1000).foreach { amount =>
      s"not be able to request a loan of less than 1000: $amount" in {
        assertThrows[IllegalArgumentException] {
          LoanAmount(amount)
        }
      }
    }

    Seq(15100, 15500).foreach { amount =>
      s"not be able to request a loan of more than 15000: $amount" in {
        assertThrows[IllegalArgumentException] {
          LoanAmount(amount)
        }
      }
    }

    Seq(1001, 1101, 2001).foreach { amount =>
      s"not be able to request a loan amount which is not an increment of 100: $amount" in {
        assertThrows[IllegalArgumentException] {
          LoanAmount(amount)
        }
      }
    }

    Seq(1000, 1200, 1500, 2000, 5000, 8100, 15000).foreach { amount =>
      s"be able to request a loan of 100 increment between 1000 and 15000: $amount" in {
        LoanAmount(amount)
      }
    }
  }
}
