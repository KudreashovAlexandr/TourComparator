package TourParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helper {
    public Helper () {}

    public static <K, V> boolean mapContainsAllKeys(Map<K, V> map, List<K> keysList){
        return map.keySet().containsAll(keysList);
    }

    public static List<Tour> getResults(List<Operator> operatorList, List<Destination> destinationList, int minPrice, int maxPrice){
        List<Tour> result = new ArrayList<>();
            for (Operator o : operatorList) {
                result.addAll(o.getOperatorObject().parseWithParameters(destinationList, minPrice, maxPrice));
            }
        return result;
    }
}
