package PlayGame.repository;

import PlayGame.entity.PlayerEntity;

import java.util.*;

public class DataBase {
    public static Map<String, Integer> pointsTableRepository=new LinkedHashMap<>();
    public static Map<String, PlayerEntity> playerRepository=new HashMap<>();
    public static Integer topPlayerPoint =0;
    public static Integer winningScore=10;
}
