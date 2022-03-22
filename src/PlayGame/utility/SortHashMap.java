package PlayGame.utility;

import java.util.*;

public class SortHashMap {

    public static Map<String, Integer> sort(Map<String,Integer> linkMap) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(linkMap.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> tempMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            tempMap.put(aa.getKey(), aa.getValue());
        }
        return tempMap;
    }
}
