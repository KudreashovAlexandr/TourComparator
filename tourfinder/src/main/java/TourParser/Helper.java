package TourParser;

import java.util.List;
import java.util.Map;

public class Helper {
    public Helper () {}

    public static <K, V> boolean mapContainsAllKeys(Map<K, V> map, List<K> keysList){
        return map.keySet().containsAll(keysList);
    }
}
