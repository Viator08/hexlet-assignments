package exercise;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        Field[] fields = object.getClass()
                .getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return field.isAnnotationPresent(NotNull.class) && field.get(object) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
// END
