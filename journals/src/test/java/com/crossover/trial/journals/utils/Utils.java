package com.crossover.trial.journals.utils;

import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.service.UserService;
import java.util.Optional;
import javax.annotation.PostConstruct;
import static org.junit.Assert.fail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author newlife
 */
@Component
public final class Utils {

    @Autowired
    private UserService us;

    private static UserService userService;

    private Utils() {
        // NOPE
    }

    @PostConstruct
    public void init() {
        userService = this.us;
    }

    public static User getUser(String name) {
        Optional<User> user = userService.getUserByLoginName(name);
        if (!user.isPresent()) {
            fail("user1 doesn't exist");
        }
        return user.get();
    }

}
