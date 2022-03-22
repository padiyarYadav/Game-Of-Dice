package PlayGame.repository;

import PlayGame.entity.PlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    public static Map<String, Integer> pointsTableRepository=new HashMap<>();
    public static Map<String, PlayerEntity> playerRepository=new HashMap<>();
}
