package org.home.game.map.objects

import spock.lang.Specification
import spock.lang.Subject

class AbstractMapObjectSpec extends Specification {

    private class MyObject extends AbstractMapObject {
        MyObject(String name, boolean isUserCharacter) {
            super(name, isUserCharacter)
        }
    }

    @Subject
    AbstractMapObject object = new MyObject('name', false)

    void 'arguments should be initialized'() {
        expect:
            with(object) {
                name == 'name'
                !isUser()
                !containAnotherObject()
                !canContainAnotherObject()
            }
    }

    void 'UnsupportedOperationException should be thrown for take method is called'() {
        when:
            object.take(Stub(MapObject))
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'UnsupportedOperationException should be thrown for clear method is called'() {
        when:
            object.clear()
        then:
            UnsupportedOperationException exception = thrown(UnsupportedOperationException)
            exception.message == 'This method is not supported.'
    }

    void 'toString method should return information about character'() {
        expect:
            object.toString() == 'MyObject{name=\'name\', isUserCharacter=false}'
    }
}
