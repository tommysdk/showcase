package tommysdk.showcase.spock

import spock.lang.*

/**
 * @author Tommy Tynj&auml;
 */
class MultiplicationSpecification extends Specification {

  def "Multiplication of two integers"() {
    when:
    def x = Multiply.these(2, 3)

    then:
    x == 6
  }

  def "Multiplication of two doubles"() {
    when:
    def x = Multiply.these(5.0, 2.0)

    then:
    x == 10.0
  }
}
