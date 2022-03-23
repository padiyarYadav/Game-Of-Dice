package PlayGame;

import PlayGame.repository.DataBase;
import PlayGame.service.AddUsersServiceImpl;
import PlayGame.service.GamePadServiceImpl;
import PlayGame.service.LeaderBoardServiceImpl;
import PlayGame.service.PlayGameServiceImpl;
import PlayGame.utility.CommonConstants;

public class PlayGame {
    private static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();
    private static PlayGameServiceImpl playGameService=new PlayGameServiceImpl();
    private static GamePadServiceImpl gamePadService=new GamePadServiceImpl();

    public static void main(String[] args) {
        do {
            startGame();
            addUsersService.addUsers();
            playGameService.RollDice();
            String winner= (String) DataBase.pointsTableRepository.keySet().toArray()[0];
            System.out.println("* "+winner+" * "+ CommonConstants.WINNING_MSG);
        }while(gamePadService.exitGame());
    }

    private static void startGame() {
        Boolean isGameStarted;
        do{
            isGameStarted=gamePadService.StartGame();
        }while (!isGameStarted);
    }

}
