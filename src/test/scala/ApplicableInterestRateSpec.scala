import org.scalatest.{Matchers, WordSpec}

class ApplicableInterestRateSpec extends WordSpec with Matchers {

 "customer" should {
   "be provided with the lowest interest rate" in {
     ApplicableInterestRate(Seq(
       Offer("A", 7.9, 10000),
       Offer("B", 3.9, 9000),
       Offer("C", 2.9, 6000),
       Offer("D", 6.9, 5000)
     ), LoanAmount(3000)) shouldBe 2.9
   }

   "be provided with the lowest interest rate from applicable offers" in {
     ApplicableInterestRate(Seq(
       Offer("A", 5.9, 10000),
       Offer("B", 3.1, 9000),
       Offer("C", 2.9, 6000),
       Offer("D", 1.9, 5000)
     ), LoanAmount(5100)) shouldBe 2.9
   }

   "receive a failure message if there are no applicable offers" in {
     assertThrows[NoOfferError] {
       ApplicableInterestRate(Seq(
         Offer("A", 5.9, 10000),
         Offer("B", 3.1, 9000),
         Offer("C", 2.9, 6000),
         Offer("D", 1.9, 5000)
       ), LoanAmount(10100))
     }
   }
 }

}
