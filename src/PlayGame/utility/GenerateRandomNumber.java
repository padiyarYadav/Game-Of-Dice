package PlayGame.utility;

public class GenerateRandomNumber {
    public static int getRandomNumber() {
        int min=CommonConstants.DICE_MIN_VAL,  max=CommonConstants.DICE_MAX_VAL+1;
        return (int) ((Math.random() * (max - min)) + min);
    }

}
