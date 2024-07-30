package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Daytime getDaytime;

    @GetMapping("/welcome")
    public String welcome() {
        var currentDaytime = getDaytime.getName();
        String result = "It is " + currentDaytime + " now! Welcome to Spring!";
        return result;
    }
}
// END
