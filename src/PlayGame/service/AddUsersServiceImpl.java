package PlayGame.service;
import PlayGame.entity.PlayerEntity;
import PlayGame.exception.CommonExceptions;
import PlayGame.repository.DataBase;
import PlayGame.utility.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddUsersServiceImpl implements AddUsersService {
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public void addUsers() {
        Integer totalPlayers=0;
        System.out.println("Please provide number of players !!!");
        try {
            String temp=sc.next();
            if(!Validate.isNumeric(temp)){
                throw new CommonExceptions("Not a number");
            }

            totalPlayers=Integer.parseInt(temp);
            if (totalPlayers < 2)
                throw new CommonExceptions("Minimum 2 players are required");
            setLeaderBoard(totalPlayers);
        }catch (CommonExceptions e){
            System.out.println(e.getMessage());
        }
    }

    void setLeaderBoard(Integer totalPlaters){
        Map leaderboardMap= new HashMap<>();
        Map playersMap= new HashMap<>();
        String name="Player-";
        for(int i=1;i<=totalPlaters;i++){
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
