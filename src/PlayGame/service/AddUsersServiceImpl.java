package PlayGame.service;
import PlayGame.entity.PlayerEntity;
import PlayGame.exception.CommonExceptions;
import PlayGame.repository.DataBase;
import PlayGame.utility.CommonConstants;
import PlayGame.utility.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddUsersServiceImpl implements AddUsersService {
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public void addUsers() {
        Integer totalPlayers=0;
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
        }catch (CommonExceptions e){
            System.out.println(e.getMessage());
        }
    }

    void setLeaderBoard(Integer totalPlayers){
        Map leaderboardMap= new HashMap<>();
        Map playersMap= new HashMap<>();
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
    }
}
