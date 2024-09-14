package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Method[] methods = address.getClass().getDeclaredMethods();
        Arrays.stream(methods).filter(m -> m.isAnnotationPresent(Inspect.class)).forEach(m -> {
            System.out.println("Method " + m.getName() + " returns a value of type " + m.getReturnType().getSimpleName());
        });
        // END
    }
}