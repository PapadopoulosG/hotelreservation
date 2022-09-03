package model;

import java.util.regex.Pattern;

public class Customer {
    public String firstName,lastName,email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
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

    public void setEmail(String email) {
        String emailRegex= "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email");
        }
        else {
            this.email = email;
        }
    }

   @Override
    public String toString(){
        return (firstName+ " "+ lastName+"   email : "+email);
    }



}
