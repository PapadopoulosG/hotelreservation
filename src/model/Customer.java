package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName,lastName,email;
    private final String emailRegex= "^(.+)@(.+).(.+)$";

    public Customer(String firstName, String lastName, String email) {

        this.isValidEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }



   @Override
    public String toString(){
        return (firstName+ " "+ lastName+"   email : "+email);
    }


    private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher m = pattern.matcher(email);
        boolean b = m.matches();

        if(!b)
        {
            throw new IllegalArgumentException("Invalid email");
        }


    }

}
