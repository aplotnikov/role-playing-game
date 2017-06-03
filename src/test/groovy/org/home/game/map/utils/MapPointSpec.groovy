package org.home.game.map.utils

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

class MapPointSpec extends Specification {
    void 'equals and hashcode contract should be followed'() {
        expect:
            EqualsVerifier.forClass(MapPoint).usingGetClass().verify()
    }
}
