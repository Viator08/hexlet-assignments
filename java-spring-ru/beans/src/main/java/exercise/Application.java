package exercise;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Qualifier("dayBean")
    public Daytime dayBean() {
        return new Day();
    }

    @Bean
    @Qualifier("nightBean")
    public Daytime nightBean() {
        return new Night();
    }

    // BEGIN
    @Bean
    @Qualifier("daytimeBean")
    public Daytime daytimeBean() {
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();

        if (hour>= 6 && hour <= 22) {
            return dayBean();
        } else {
            return nightBean();
        }
    }
    // END
}
