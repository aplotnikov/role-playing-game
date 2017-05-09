package org.home.game;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.map.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GamePlay {

    @NonNull
    Map map;

    public void start() {
        while (map.containsUserCharacter() && map.containsTasks()) {
            map.goToNextIteration();
        }
    }
}
