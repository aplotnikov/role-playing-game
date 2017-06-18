package org.home.game.common.utils

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class IntRangeSpec extends Specification {

    @Subject
    IntRange range = IntRange.of(1, 5)

    void 'IllegalStateException should be thrown when min value is greater than max'() {
        when:
            IntRange.of(2, 1)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'Min value is greater than max. Min: 2, Max: 1'
    }

    void 'range should return #expectedResult when value is #value'() {
        expect:
            range.contains(value) == expectedResult
        where:
            value || expectedResult
            1     || true
            3     || true
            5     || true
            0     || false
            6     || false
    }
}
