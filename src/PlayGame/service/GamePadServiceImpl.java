package PlayGame.service;

import java.util.Locale;
import java.util.Scanner;

public class GamePadServiceImpl implements GamePadService{
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public Boolean rollDice(String player)  {
        System.out.println(player+" please press key r to roll the dice....");
        String inputString=sc.next();
        if(inputString.toLowerCase(Locale.ROOT).equals("r"))
        return true;
        return false;
    }

    @Override
    public Boolean exitGame() {
        return null;
    }

    @Override
    public Boolean StartGame() {
        System.out.println("Please press any key to start the game....");
        sc.nextLine();
        return true;
    }
}
