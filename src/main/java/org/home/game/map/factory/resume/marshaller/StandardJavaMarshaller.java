package org.home.game.map.factory.resume.marshaller;

import org.home.game.map.entities.Entity;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import static java.nio.file.Files.notExists;

public class StandardJavaMarshaller implements Marshaller {

    @Override
    public void marshall(List<List<Entity>> entities, String path) {
        Path filePath = Paths.get(path);
        createFileIfNotExist(filePath);
        try (ObjectOutput output = new ObjectOutputStream(newOutputStream(filePath))) {
            output.writeObject(entities);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void createFileIfNotExist(Path path) {
        if (exists(path)) {
            return;
        }
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<List<Entity>> unmarshall(String path) {
        Path filePath = Paths.get(path);
        if (notExists(filePath)) {
            throw new IllegalStateException("Such file does not exist. Path: " + filePath.toAbsolutePath().toString());
        }
        try (ObjectInput input = new ObjectInputStream(newInputStream(filePath))) {
            //noinspection unchecked
            return (List<List<Entity>>) input.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
