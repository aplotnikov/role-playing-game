package org.home.game.scene.character.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter
@AllArgsConstructor
public class Character {

    String name;

    Race race;

    Sex sex;
}
