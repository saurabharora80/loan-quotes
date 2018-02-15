import org.scalatest.{Matchers, WordSpec}

class GenerateQuoteSpec extends WordSpec with Matchers {

  "quote generation" should {
    "fail with error message if offers file is not present" in {
      GenerateQuote("/invalid/file/path.csv", "1000") shouldBe "/invalid/file/path.csv (No such file or directory)"
    }

    "fail with error message if amount is not a number" in {
      GenerateQuote(getClass.getResource("market_data.csv").getFile, "abc") shouldBe "Invalid loan amount"
    }

    "fail with error message if amount is empty string" in {
      GenerateQuote(getClass.getResource("market_data.csv").getFile, "") shouldBe "Invalid loan amount"
    }

    "not produce a suitable quote if offers file is empty" in {
      val emptyFile = getClass.getResource("market_data_empty.csv").getFile
      GenerateQuote(emptyFile, "1000") shouldBe "Unable to find a suitable offer for loan of 1000"
    }

    "find a suitable quote" in {
      GenerateQuote(getClass.getResource("market_data.csv").getFile, "1000") shouldBe
        """Requested amount: £1000
          |Rate: 6.9%
          |Monthly repayment: £30.84
          |Total repayment: £1110.24""".stripMargin
    }

    "find a suitable quote while ignoring the empty lines" in {
      GenerateQuote(getClass.getResource("market_data_with_empty_lines.csv").getFile, "1000") shouldBe
        """Requested amount: £1000
          |Rate: 6.9%
          |Monthly repayment: £30.84
          |Total repayment: £1110.24""".stripMargin
    }
  }

}
