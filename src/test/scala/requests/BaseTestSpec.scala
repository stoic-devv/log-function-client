package requests

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.{BeforeAndAfter, Inside, Inspectors, OptionValues}

class BaseTestSpec extends AnyFlatSpec with should.Matchers with OptionValues with Inside with Inspectors with BeforeAndAfter {

}