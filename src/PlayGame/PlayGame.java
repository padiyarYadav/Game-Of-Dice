package PlayGame;

import PlayGame.service.AddUsersServiceImpl;
import PlayGame.service.GamePadServiceImpl;
import PlayGame.service.LeaderBoardServiceImpl;
import PlayGame.service.PlayGameServiceImpl;

public class PlayGame {
    private static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();
    private static PlayGameServiceImpl playGameService=new PlayGameServiceImpl();
    private static GamePadServiceImpl gamePadService=new GamePadServiceImpl();
    private static LeaderBoardServiceImpl leaderBoardService=new LeaderBoardServiceImpl();

    public static void main(String[] args) {
        do {
            startGame();
            addUsersService.addUsers();
            playGameService.RollDice();
            leaderBoardService.showLeaderBoard();
        }while(gamePadService.exitGame());
    }

    private static void startGame() {
        Boolean isGameStarted;
        do{
            isGameStarted=gamePadService.StartGame();
        }while (!isGameStarted);
    }

}
