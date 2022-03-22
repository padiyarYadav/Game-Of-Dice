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
        Boolean isGameStarted;
        do{
            isGameStarted=gamePadService.StartGame();
        }while (!isGameStarted);
        addUsersService.addUsers();
        playGameService.RollDice();
        leaderBoardService.showLeaderBoard();


    }
}
