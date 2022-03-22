package PlayGame;

import PlayGame.repository.DataBase;
import PlayGame.service.AddUsersServiceImpl;
import PlayGame.service.GamePadServiceImpl;
import PlayGame.service.PlayGameServiceImpl;

public class PlayGame {
    private static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();
    private static PlayGameServiceImpl playGameService=new PlayGameServiceImpl();
    private static GamePadServiceImpl gamePadService=new GamePadServiceImpl();

    public static void main(String[] args) {
        Boolean isGameStarted;
        do{
            isGameStarted=gamePadService.StartGame();
        }while (!isGameStarted);
        addUsersService.addUsers();
        playGameService.RollDice();


    }
}
