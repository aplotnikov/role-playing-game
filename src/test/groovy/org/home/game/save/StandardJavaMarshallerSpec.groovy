package org.home.game.save

import static org.home.game.map.GameMapBuilder.map
import static org.home.game.map.entities.EntityFactory.bear
import static org.home.game.map.entities.EntityFactory.character
import static org.home.game.map.entities.EntityFactory.road
import static org.home.game.map.entities.EntityFactory.stone
import static org.home.game.map.entities.EntityFactory.tree
import static org.home.game.map.entities.EntityFactory.userCharacter
import static org.home.game.map.entities.EntityFactory.wolf
import static org.home.game.map.entities.character.Race.HUMAN
import static org.home.game.map.entities.character.Race.ORC
import static org.home.game.map.entities.character.Sex.FEMALE
import static org.home.game.map.entities.character.Sex.MALE

import org.home.game.map.entities.Entity
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class StandardJavaMarshallerSpec extends Specification {

    @Rule
    TemporaryFolder folder = new TemporaryFolder()

    @Shared
    String fileName = 'my_object.txt'

    @Subject
    StandardJavaMarshaller marshaller = new StandardJavaMarshaller()

    List<List<Entity>> entities = map()
            .line(road(), road(), tree())
            .line(stone(), road(userCharacter('Andrii', HUMAN, MALE)), road(wolf()))
            .line(road(bear()), road(character('Orc', ORC, FEMALE)), tree())
            .create()

    void 'entities should be serialized into file and deserialized from file'() {
        given:
            File file = folder.newFile(fileName)
        when:
            marshaller.marshall(entities, file.path)
        then:
            old(file.text.isEmpty())
        and:
            !file.text.isEmpty()
        when:
            List<List<Entity>> result = marshaller.unmarshall(file.path)
        then:
            result == entities
    }

    void 'entities should be serialized into file and file should be created automatically'() {
        given:
            String filePath = folder.newFolder().path + fileName
            File file = new File(filePath)
        when:
            marshaller.marshall(entities, filePath)
        then:
            !old(file.exists())
        and:
            file.exists()
            !file.text.isEmpty()
    }

    void 'IllegalStateException should be thrown when file does not exist'() {
        when:
            marshaller.unmarshall('not_exist_file.txt')
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message.startsWith 'Such file does not exist. Path:'
            exception.message.endsWith 'not_exist_file.txt'
    }
}
