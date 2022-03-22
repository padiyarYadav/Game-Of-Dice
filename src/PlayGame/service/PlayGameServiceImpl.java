package PlayGame.service;

import PlayGame.entity.PlayerEntity;
import PlayGame.repository.DataBase;
import PlayGame.utility.CommonConstants;
import PlayGame.utility.GenerateRandomNumber;
import PlayGame.utility.SortHashMap;

import java.util.*;

public class PlayGameServiceImpl implements PlayGameService{
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public void RollDice() {
        Set<String> playersSet=DataBase.pointsTableRepository.keySet();

        do{
            //            Get top player in the leaderboard
            getTopPlayer();

            for(String player:playersSet){
                Integer diceValue;
                if(isPlayerPenalised(player)){
//                    continue or skip players turn
                    continue;
                }
                do {
                    diceValue= GenerateRandomNumber.getRandomNumber();
                    populatePlayerRepository(player, diceValue);
                }while (diceValue == CommonConstants.BONUS_TURN_POINT&&DataBase.topPlayerPoint <=CommonConstants.WINNING_POINT);
                if(DataBase.topPlayerPoint >CommonConstants.WINNING_POINT){
                    break;
                }
            }
        } while(DataBase.topPlayerPoint <CommonConstants.WINNING_POINT);
    }

    private boolean isPlayerPenalised(String player) {
        if(DataBase.playerRepository.get(player).isBlocked()){
            DataBase.playerRepository.get(player).setBlocked(false);
            return true;
        }
        return false;
    }

    public void populatePlayerRepository(String player, Integer diceValue){
        int currentPoint=DataBase.pointsTableRepository.get(player);

        Integer newPoint=currentPoint+diceValue;
        DataBase.pointsTableRepository.put(player,newPoint);
        DataBase.playerRepository.get(player).setScore(newPoint);
        populateScoreHistoryLog(DataBase.playerRepository.get(player),diceValue);
        DataBase.pointsTableRepository=SortHashMap.sort(DataBase.pointsTableRepository);
        getTopPlayer();
    }

    private void populateScoreHistoryLog(PlayerEntity playerEntity,Integer diceValue) {
        if(playerEntity.getScoreHistory().size()<CommonConstants.PENALTY_NUMBER_OCCUR_VAL){
            playerEntity.getScoreHistory().add(diceValue);
        }else {
            playerEntity.getScoreHistory().remove(0);
            playerEntity.getScoreHistory().add(diceValue);
        }
        int penaltyCounter=0;
        for (int i = playerEntity.getScoreHistory().size(); i-- > 0; ) {
            if(playerEntity.getScoreHistory().get(i)==CommonConstants.PENALTY_POINT)
                penaltyCounter++;
        }
        if(penaltyCounter>=CommonConstants.PENALTY_NUMBER_OCCUR_VAL)
            playerEntity.setBlocked(true);
        return;
        }



    public static void getTopPlayer(){
        DataBase.topPlayerPoint = DataBase.pointsTableRepository.values().iterator().next();
    }
}
