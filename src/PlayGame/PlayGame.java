package PlayGame;

import PlayGame.repository.DataBase;
import PlayGame.service.AddUsersServiceImpl;

public class PlayGame {
    static AddUsersServiceImpl addUsersService=new AddUsersServiceImpl();

    public static void main(String[] args) {
        addUsersService.addUsers();
    }
}
