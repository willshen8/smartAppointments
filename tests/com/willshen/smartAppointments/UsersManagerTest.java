package com.willshen.smartAppointments;

import com.willshen.smartAppointments.User;
import com.willshen.smartAppointments.UserManager;
import com.willshen.smartAppointments.UserType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsersManagerTest {

    @Test
    void registerUser() {
        UserManager userManager = new UserManager();
        User user = new User();
        user.setName("Will Shen");
        user.setEmail("will.shen@anz.com");
        user.setPassword("topsecret");
        user.setUserType(UserType.USER);
        LocalDate date = LocalDate.now();
        user.setDob(date);
        user.setAddress("8", "Bond", "Street", "Dockland",
                3000, "Victoria", "Australia");
        assertTrue(userManager.registerUser(user));
    }

    @Test
    void validateUser() {
        UserManager userManager = new UserManager();
        User user = new User();
        user.setName("Will Shen");
        user.setEmail("will.shen@anz.com");
        LocalDate date = LocalDate.now();
        user.setUserType(UserType.USER);
        user.setDob(date);
        user.setAddress("8", "Bond", "Street", "Dockland",
                3000, "Victoria", "Australia");
        assertFalse(userManager.validateUser(user));
    }

    @Test
    void getNotNullUser() {
        UserManager usersManager = new UserManager();
        User userFromDB = usersManager.getUser("will.shen@anz.com");
        assertNotNull(userFromDB);
    }

    @Test
    void getNullUser() {
        UserManager usersManager = new UserManager();
        User userFromDB = usersManager.getUser("random@email.com");
        assertNull(userFromDB);
    }

    @Test
    void removeUser() {
    }
}