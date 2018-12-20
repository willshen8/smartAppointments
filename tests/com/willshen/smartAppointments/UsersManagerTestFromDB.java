package com.willshen.smartAppointments;

import com.willshen.smartAppointments.User;
import com.willshen.smartAppointments.UserManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersManagerTestFromDB {
    @Test
    void addNewUserIntoDB() {
        UserManager userManager = new UserManager();
        User user = new User();
        user.setName("Will Shen");
        user.setEmail("will.shen@anz.com");
        user.setPassword("topsecret");
        LocalDate date = LocalDate.now();
        user.setDob(date);
        user.setAddress("8", "Bond", "Street", "Dockland",
                3000, "Victoria", "Australia");
        assertTrue(userManager.registerUser(user));
    }

    @Test
    void validateUser() {

    }

    @Test
    void getUser() {

    }
}
