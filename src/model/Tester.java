package model;

import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args){

        Customer customer = new Customer("first","second","j@domain.com");
       // System.out.println(customer);


        FreeRoom freeRoom = new FreeRoom("100",20.0,RoomType.SINGLE,true);
      //  System.out.println(freeRoom);

/*
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,8,27);
        date = calendar.getTime();

        Reservation reserve = new Reservation(customer,freeRoom, date,date );


        System.out.println(reserve);
*/

    }
}
