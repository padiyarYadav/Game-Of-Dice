package PlayGame.service;

import PlayGame.utility.CommonConstants;

import java.util.Locale;
import java.util.Scanner;

public class GamePadServiceImpl implements GamePadService{
    private static final Scanner sc=new Scanner(System.in);

    @Override
    public Boolean rollDice(String player)  {
        System.out.println(player+ CommonConstants.ROLLING_DICE_KEY_MSG);
        String inputString=sc.next();
        if(inputString.toLowerCase(Locale.ROOT).equals(CommonConstants.ROLLING_DICE_KEY))
        return true;
        return false;
    }

    @Override
    public Boolean exitGame() {
        return null;
    }

    @Override
    public Boolean StartGame() {
        System.out.println(CommonConstants.PRESS_KEY_TO_START_MESSAGE);
        sc.nextLine();
        return true;
    }
}
