package PlayGame.service;

import PlayGame.entity.PlayerEntity;
import PlayGame.repository.DataBase;
import PlayGame.utility.CommonConstants;
import PlayGame.utility.GenerateRandomNumber;
import PlayGame.utility.InputHandler;
import PlayGame.utility.SortHashMap;

import java.util.*;
import java.util.stream.Collectors;

public class PlayGameServiceImpl implements PlayGameService{
    private static final Scanner inputHandler = InputHandler.getScannerObject();
    private static GamePadServiceImpl gamePadService=new GamePadServiceImpl();
    private static LeaderBoardServiceImpl leaderBoardService=new LeaderBoardServiceImpl();

    @Override
    public void RollDice() {
        Set<String> playersSet=DataBase.pointsTableRepository.keySet();

        do{
            //            Get top player in the leaderboard
            getTopPlayer();

            for(String player:playersSet){
                Integer diceValue;
                Boolean isDiceRolled;
                if(isPlayerPenalised(player)){
//                    continue or skip players turn
                    continue;
                }
                do {
                    do
                    {
                        isDiceRolled=gamePadService.rollDice(player);
                    }while(!isDiceRolled);
                    diceValue= GenerateRandomNumber.getRandomNumber();
                    populatePlayerRepository(player, diceValue);
                    if(DataBase.playerRepository.get(player).getScore() >=DataBase.winningScore){
                        DataBase.playerRepository.get(player).setBlocked(true);
                        break;
                    }
                }while (diceValue == CommonConstants.BONUS_TURN_POINT);
                if(DataBase.topPlayerPoint==-1){
                    break;
                }
            }
            if(DataBase.topPlayerPoint==-1){
                break;
            }
        } while(DataBase.topPlayerPoint <DataBase.winningScore);
    }


    /**
     *
     * Is player penalised
     *
     * @param player  the player
     * @return boolean
     */
    private boolean isPlayerPenalised(String player) {
        if(DataBase.playerRepository.get(player).isBlocked()){
            if(DataBase.playerRepository.get(player).getScore()<DataBase.winningScore)
                    DataBase.playerRepository.get(player).setBlocked(false);
            return true;
        }
        return false;
    }

    /**
     *
     * Populate player repository
     *
     * @param player  the player
     * @param diceValue  the dice value
     */
    public void populatePlayerRepository(String player, Integer diceValue){
//      dicevalue=  even
        int currentPoint=DataBase.pointsTableRepository.get(player);


        Integer newPoint=currentPoint+diceValue;

        if(diceValue%2==0) {
            newPoint += diceValue;
            updateOtherPlayersRecord(diceValue,player);
        }
        DataBase.pointsTableRepository.put(player,newPoint);
        DataBase.playerRepository.get(player).setScore(newPoint);
        populateScoreHistoryLog(DataBase.playerRepository.get(player),diceValue);
        DataBase.pointsTableRepository=SortHashMap.sort(DataBase.pointsTableRepository);
        System.out.println(CommonConstants.STARS_DESIGN_1+player+CommonConstants.ROLLED_NUMBER+diceValue+CommonConstants.STARS_DESIGN_2);
        System.out.println();
        getTopPlayer();
        leaderBoardService.showLeaderBoard();

    }

    private void updateOtherPlayersRecord(Integer diceValue,String mainPlayer) {
        List<String> playersList =filterMainPlayer(DataBase.pointsTableRepository.keySet(),mainPlayer);
        playersList=filterWinners(playersList);

        playersList.stream().forEach(player->{

//            Points table population
            int currentPoint=DataBase.pointsTableRepository.get(player);
            currentPoint-=diceValue;

            DataBase.pointsTableRepository.put(player,currentPoint<0?0:currentPoint);

//            User table population
            currentPoint=DataBase.playerRepository.get(player).getScore();
            currentPoint-=diceValue;
            DataBase.playerRepository.get(player).setScore(currentPoint<0?0:currentPoint);
        });
        System.out.println("All players point deducted by "+diceValue);


    }

    private List<String> filterWinners(List<String> playersList) {

        return playersList.stream().filter(player->
                DataBase.playerRepository.get(player).getScore()<DataBase.winningScore)
                .collect(Collectors.toList());
    }

    private List<String> filterMainPlayer(Set data,String player){
        return (List<String>) data.stream().filter(p->!p.equals(player)).collect(Collectors.toList());
    }

    /**
     *
     * Populate score history log
     *
     * @param playerEntity  the player entity
     * @param diceValue  the dice value
     */
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
        if(penaltyCounter>=CommonConstants.PENALTY_NUMBER_OCCUR_VAL) {
            System.out.println(playerEntity.getName()+CommonConstants.PENALTY_MESSAGE);
            playerEntity.setBlocked(true);
        }
        return;
        }


    /**
     *
     * Gets the top player from leader board
     *
     */
    private static void getTopPlayer(){
        DataBase.topPlayerPoint=-1;
        DataBase.pointsTableRepository.keySet().stream().forEach(player->{
            DataBase.topPlayerPoint=DataBase.playerRepository.get(player).isBlocked()&&
                    DataBase.playerRepository.get(player).getScore()>= DataBase.winningScore?
                    DataBase.topPlayerPoint:
                    DataBase.playerRepository.get(player).getScore();
        });
    }
}
