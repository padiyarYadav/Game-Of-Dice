package PlayGame;

import PlayGame.repository.DataBase;
import PlayGame.service.AddUsersServiceImpl;
import PlayGame.service.PlayGameServiceImpl;

public class PlayGame {
    private static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();
    private static PlayGameServiceImpl playGameService=new PlayGameServiceImpl();
    public static void main(String[] args) {
        addUsersService.addUsers();
        playGameService.RollDice();

    }
}
