package PlayGame.service;
import PlayGame.entity.PlayerEntity;
import PlayGame.exception.CommonExceptions;
import PlayGame.repository.DataBase;
import PlayGame.utility.*;

import java.util.*;

public class AddUsersServiceImpl implements AddUsersService {
    private static final Scanner sc=new Scanner(System.in);
    private static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();

    @Override
    public void addUsers() {
        Integer totalPlayers;
        System.out.println(CommonConstants.TOTAL_PLAYER_INP_MSG);
        try {
            String temp=sc.next();
            if(!Validate.isNumeric(temp)){
                throw new CommonExceptions(CommonConstants.TOTAL_PLAYER_INP_ERR_MSG);
            }

            totalPlayers=Integer.parseInt(temp);
            if (totalPlayers < 2)
                throw new CommonExceptions(CommonConstants.WRONG_PLAYER_NUMBER_MSG);
            setLeaderBoard(totalPlayers);

            System.out.println(CommonConstants.WINNING_SCORE_MSG);
            temp=sc.next();
            if(!Validate.isNumeric(temp)){
                throw new CommonExceptions(CommonConstants.TOTAL_PLAYER_INP_ERR_MSG);
            }
            DataBase.winningScore= Integer.valueOf(temp);

        }catch (CommonExceptions e){
            System.out.println(e.getMessage());
            addUsersService.addUsers();
        }
    }

    /**
     *
     * Sets the leader board
     *
     * @param totalPlayers  the total players
     */
   private static void setLeaderBoard(Integer totalPlayers){
        Map<String, Integer> leaderboardMap= new LinkedHashMap<>();
        Map<String, PlayerEntity> playersMap= new LinkedHashMap<>();
        String name= CommonConstants.PLAYER_NAME;
        for(int i=1;i<=totalPlayers;i++){
            leaderboardMap.put(name+i,0);

            playersMap.put(name+i,new PlayerEntity(i,name+i));
        }
        DataBase.pointsTableRepository=leaderboardMap;
        DataBase.playerRepository=playersMap;
    }

    @Override
    public void deleteUser() {
//       Implement delete user (Future scope)
    }
}
