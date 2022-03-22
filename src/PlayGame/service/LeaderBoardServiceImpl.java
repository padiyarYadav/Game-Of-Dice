package PlayGame.service;

import PlayGame.repository.DataBase;
import PlayGame.utility.CommonConstants;

import java.util.concurrent.atomic.AtomicReference;

public class LeaderBoardServiceImpl implements LeaderBoardService{
    @Override
    public void showLeaderBoard() {
        printHeader();
        AtomicReference<Boolean> isWinner= new AtomicReference<>(true);
        DataBase.pointsTableRepository.keySet().stream().forEach(player->{
            int len=DataBase.pointsTableRepository.get(player).toString().length();
            System.out.println("| "+player+" | "+DataBase.pointsTableRepository.get(player)+" ".repeat(6-len)+"|"+(isWinner.get() ?" \"Winner\"":""));
            isWinner.set(false);

        });
        System.out.println(CommonConstants.LINE);
    }

    private void printHeader() {

        System.out.println(CommonConstants.LEADER_BOARD_TITLE);
        System.out.println(CommonConstants.LINE);
        System.out.println(CommonConstants.LEADER_BOARD_HEADING);
        System.out.println(CommonConstants.LINE);
    }

}