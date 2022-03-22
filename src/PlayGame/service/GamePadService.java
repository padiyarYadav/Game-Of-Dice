package PlayGame.service;

import java.io.IOException;

public interface GamePadService {

    Boolean rollDice(String player) throws IOException;
    Boolean exitGame();
    Boolean StartGame() throws IOException;
}
