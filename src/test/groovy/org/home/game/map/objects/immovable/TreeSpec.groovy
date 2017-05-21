package org.home.game.map.objects.immovable

import static org.home.game.map.objects.immovable.Tree.tree

import spock.lang.Specification
import spock.lang.Subject

class TreeSpec extends Specification {
    @Subject
    Tree tree = tree()

    void 'default parameters should be initialized'() {
        expect:
            with(tree) {
                name == 'Tree'
                !isUser()
            }
    }
}
