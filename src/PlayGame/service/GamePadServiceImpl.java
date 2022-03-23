package PlayGame.service;

import PlayGame.utility.CommonConstants;
import PlayGame.utility.InputHandler;

import java.util.Locale;
import java.util.Scanner;

public class GamePadServiceImpl implements GamePadService{
    private static final Scanner inputHandler = InputHandler.getScannerObject();

    @Override
    public Boolean rollDice(String player)  {
        System.out.println(player+ CommonConstants.ROLLING_DICE_KEY_MSG);
        String inputString= inputHandler.next();
        if(inputString.toLowerCase(Locale.ROOT).equals(CommonConstants.ROLLING_DICE_KEY))
        return true;
        return false;
    }

    @Override
    public Boolean exitGame() {
        System.out.println(CommonConstants.EXIT_MSG);
        if(inputHandler.next().toLowerCase(Locale.ROOT).equals(CommonConstants.ROLLING_DICE_KEY))
         return true;
        System.out.println(CommonConstants.GAME_OVER_MSG);
        return false;
    }

    @Override
    public Boolean StartGame() {
        System.out.println(CommonConstants.PRESS_KEY_TO_START_MESSAGE);
        inputHandler.nextLine();
        System.out.println(CommonConstants.GAME_STARTED_MSG);
        return true;
    }
}
