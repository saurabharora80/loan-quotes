import org.scalatest.{Matchers, WordSpec}

class OffersSpec extends WordSpec with Matchers {

  "offers" should {
    "convert raw offers data into Seq[Offer]" in {
      Offers(Seq(
        "Bob,0.075,640",
        "Jane,0.069,480",
        "Fred,0.071,520"
      )) should contain allElementsOf Seq(
        Offer("Bob", 7.5, 64000),
        Offer("Jane", 6.9, 48000),
        Offer("Fred", 7.1, 52000)
      )
    }

    Seq("0.0ab", "abc", "0.1a3", "").foreach { invalidRate =>
      s"fail to convert if rate is invalid: $invalidRate" in {
        assertThrows[InvalidOfferException] {
          Offers(Seq(
            s"Bob,$invalidRate,640",
            "Jane,0.069,480",
            "Fred,0.071,520"
          ))
        }
      }
    }

    Seq("56c", "abc", "56.3", "0.456", "").foreach { invalidAmount =>
      s"fail to convert if maximum loan amount is invalid: $invalidAmount" in {
        assertThrows[InvalidOfferException] {
          Offers(Seq(
            s"Bob,0.076,$invalidAmount",
            "Jane,0.069,480",
            "Fred,0.071,520"
          ))
        }
      }
    }

    s"fail to convert offer data is incomplete" in {
      assertThrows[InvalidOfferException] {
        Offers(Seq(
          s"Bob,0.076,580",
          "0.069,480",
          "Fred,0.071,520"
        ))
      }
    }

  }

}

