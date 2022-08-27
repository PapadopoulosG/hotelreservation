package model;

import java.util.Date;

public class Reservation {
    public Customer customer;
    public IRoom room;
    Date checkInDate;
    Date checkOutDate;

    /*o constructor na diagrafei an de paei kala
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
*/
    @Override
    public String toString(){
        return "\n Customer "+customer+" has reserved room "+room+" and is checking in on "+
                checkInDate+" and checking out on "+checkOutDate;
    }

}
