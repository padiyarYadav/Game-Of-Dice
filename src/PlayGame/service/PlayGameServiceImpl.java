package PlayGame.service;

import PlayGame.repository.DataBase;
import PlayGame.utility.CommonConstants;
import PlayGame.utility.GenerateRandomNumber;
import PlayGame.utility.SortHashMap;

import java.util.*;

public class PlayGameServiceImpl implements PlayGameService{
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public Integer RollDice() {
        Set<String> playersSet=DataBase.pointsTableRepository.keySet();

        do{
            //            Get top player in the leaderboard
            getTopPlayer();

            for(String player:playersSet){
                Integer diceValue;
                do {
                    diceValue=GenerateRandomNumber.getRandomNumber();
                    populatePlayerRepository(player, diceValue);
                }while (diceValue== CommonConstants.BONUS_TURN_POINT&&DataBase.topPlayerPoint <=CommonConstants.WINNING_POINT);
                if(DataBase.topPlayerPoint >CommonConstants.WINNING_POINT){
                    break;
                }
            }
        } while(DataBase.topPlayerPoint <CommonConstants.WINNING_POINT);

        return 0;
    }

    public void populatePlayerRepository(String player, Integer diceValue){
        int currentPoint=DataBase.pointsTableRepository.get(player);

        Integer newPoint=currentPoint+diceValue;
        DataBase.pointsTableRepository.put(player,newPoint);
        DataBase.playerRepository.get(player).setScore(newPoint);
        DataBase.pointsTableRepository=SortHashMap.sort(DataBase.pointsTableRepository);
        getTopPlayer();
    }

    public static void getTopPlayer(){
        DataBase.topPlayerPoint = DataBase.pointsTableRepository.values().iterator().next();
    }
}
