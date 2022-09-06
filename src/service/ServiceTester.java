/*
package service;

import model.Customer;
import model.Reservation;
import model.Room;
import service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


import static model.RoomType.SINGLE;

public class ServiceTester {
    public static void main(String[] args) throws ParseException {



        CustomerService test = new CustomerService();

        test.addCustomer("first","second","a@a.com");
        test.addCustomer("two","three","b@b.com");
       // System.out.println(test.getAllCustomers());
       // System.out.println(test.getCustomer("b@b.com"));

        ReservationService reservationTest = new ReservationService();

        reservationTest.addRoom(new Room("3",2.0, SINGLE,false));
      //  System.out.println(reservationTest.getRoom("3"));



        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        Date checkInDate = simpleDateFormat.parse("09-08-2000");
        Date checkOutDate = simpleDateFormat.parse("10-09-2000");



        reservationTest.reserveARoom(test.getCustomer("a@a.com"), reservationTest.getRoom("3"),
                checkInDate,checkOutDate);

        reservationTest.printAllReservation();

        enum test1{
            one , two, three
        }

//        String pap = String.valueOf(test1.valueOf("one"));
        test1 pap = test1.one;
        String papa = String.valueOf(pap);

        System.out.println("\n "+ papa);



    }


}
*/
