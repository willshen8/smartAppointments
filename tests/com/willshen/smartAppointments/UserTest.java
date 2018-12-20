package com.willshen.smartAppointments;

import com.willshen.smartAppointments.User;
import com.willshen.smartAppointments.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setName() {
        User testUserWithValidName = new User();
        testUserWithValidName.setName("Will");
        assertEquals("Will", testUserWithValidName.getName());
    }

    @Test
    void setEmptyName() {
        User testUserWithValidName = new User();
        testUserWithValidName.setName("");
        assertEquals(null, testUserWithValidName.getName());
    }

    @Test
    void setDob() {
        User testUserWithValidName = new User();
        LocalDate dob = LocalDate.of(1988,8,8);
        testUserWithValidName.setDob(dob);
        assertEquals(dob, testUserWithValidName.getDob());
    }

    @Test
    void setAddress() {
        User testUserWithValidAddress = new User();
        testUserWithValidAddress.setAddress("8", "Bond", "Street", "Dockland",
                3000, "Victoria", "Australia");
        
    }

    @Test
    void setValidEmail() {
        User testUserWithValidEmail = new User();
        testUserWithValidEmail.setEmail("will.shen@anz.com");
        Boolean result = Util.checkEmail(testUserWithValidEmail.getEmail());
        assertEquals(true, result);
    }

    @Test
    void setInvalidEmail() {
        User testUserWithInvalidEmail = new User();
        testUserWithInvalidEmail.setEmail("will.shen@");
        Boolean result = Util.checkEmail(testUserWithInvalidEmail.getEmail());
        assertEquals(true, false);
    }

    @Test
    void setInvalidEmail2() {
        User testUserWithInvalidEmail = new User();
        testUserWithInvalidEmail.setEmail("will.shen@anz");
        Boolean result = Util.checkEmail(testUserWithInvalidEmail.getEmail());
        assertEquals(false, result);
    }

    @Test
    void setInvalidEmail3() {
        User testUserWithInvalidEmail = new User();
        testUserWithInvalidEmail.setEmail("@anz.com");
        Boolean result = Util.checkEmail(testUserWithInvalidEmail.getEmail());
        assertEquals(false, result);
    }

    @Test
    void setPhone() {
    }

    @Test
    void setAppointments() {
    }
}