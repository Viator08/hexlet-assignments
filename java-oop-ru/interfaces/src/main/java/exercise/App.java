package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        return list.stream()
                .sorted(Comparator.comparingDouble(Home::area))
                .limit(n)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
