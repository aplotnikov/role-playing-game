package org.home.game.play;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.home.game.map.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Game {

    @NonNull
    Map map;

    public void start() {
        map.draw();
        while (map.containsUserCharacter() && map.containsTasks()) {
            map.goToNextIteration();
            map.redraw();
        }
    }
}
