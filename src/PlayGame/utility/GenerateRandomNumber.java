package PlayGame.utility;

public class GenerateRandomNumber {
    public static int getRandomNumber() {
        int min=1,  max=7;
        return (int) ((Math.random() * (max - min)) + min);
    }

}
