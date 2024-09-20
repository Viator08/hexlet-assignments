package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN

@RestController
public class WelcomeController {

    @Autowired
    @Qualifier("daytimeBean")
    private Daytime daytime;

    @GetMapping("/welcome")
    public String welcome() {
        return "It is " + daytime.getName() + " now! \nWelcome to Spring!";
    }
}
// END
