package com.willshen.smartAppointments;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

enum UserType {
    USER, STAFF, ADMIN
}

@Entity
public class User {
    private String name;
    private LocalDate dob;
    @Id private String email;
    private String phone;
    private String password;
    @Column(nullable=false)
    private UserType userType;

    //Address fields
    private String houseNumber;
    private String streetName;
    private String street_type;
    private String suburb;
    private int postCode;
    private String state;
    private String country;

    public User() {
    }

    /**
     *
     * @param name Full name of the user.
     * @param dob Date of birth of the user.
     * @param email Email of the user.
     * @param phone Phone can be home, business or mobile number.
     */


    // Constructor that takes only the mandatory fields
    public User(String name, LocalDate dob, String email, String phone, String password, UserType userType) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
    }

    // Constructor that takes all the fields
    public User(String name, LocalDate dob, String email, String phone, String password, UserType userType,
                String houseNumber, String streetName, String street_type, String suburb,
                int postCode, String state, String country) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.street_type = street_type;
        this.suburb = suburb;
        this.postCode = postCode;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() { return userType;}

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreet_type() {
        return street_type;
    }

    public String getSuburb() {
        return suburb;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        if(name== "" | name == null ){
            this.name = null;
        } else{
            this.name = name;
        }
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Sets the email field on the User class
     * @param email Email of the user, it will be validated.
     */
    public void setEmail(String email) {
        if(Util.checkEmail(email)){
            this.email = email;
        } else {
            this.email = null;
        }
    }

    public void setPhone(String phone) {
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreet_type(String street_type) {
        this.street_type = street_type;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String houseNumber,
                           String streetName,
                           String street_type,
                           String suburb,
                           int postCode,
                           String state,
                           String country){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.street_type = street_type;
        this.suburb = suburb;
        this.postCode = postCode;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"dob\":\"" + dob + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"phone\":\"" + phone + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"userType\":\"" + userType + '\"' +
                ", \"houseNumber\":\"" + houseNumber + '\"' +
                ", \"streetName\":\"" + streetName + '\"' +
                ", \"street_type\":\"" + street_type + '\"' +
                ", \"suburb\":\"" + suburb + '\"' +
                ", \"postCode\":\"" + postCode + '\"' +
                ", \"state\":\"" + state + '\"' +
                ", \"country\":\"" + country + '\"' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return postCode == user.postCode &&
                Objects.equals(name, user.name) &&
                Objects.equals(dob, user.dob) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(password, user.password) &&
                userType == user.userType &&
                Objects.equals(houseNumber, user.houseNumber) &&
                Objects.equals(streetName, user.streetName) &&
                Objects.equals(street_type, user.street_type) &&
                Objects.equals(suburb, user.suburb) &&
                Objects.equals(state, user.state) &&
                Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob, email, phone, password, userType, houseNumber, streetName, street_type, suburb, postCode, state, country);
    }
}
