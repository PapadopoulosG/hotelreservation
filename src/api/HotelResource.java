package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final HotelResource INSTANCE = new HotelResource();
    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    //na dokimaso na bgalo to apo kato na do ti ginetai
    private HotelResource(){}

    public static HotelResource getInstance() {
        return INSTANCE;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String firstName, String lastName, String email)
    {
        customerService.addCustomer(firstName,lastName,email);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail,IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        //edo exei kapois allages to diko toy na to koitakso an de doyleyei kala
        //an customer == null?
        return reservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return reservationService.findRooms(checkInDate,checkOutDate);
    }

    public Collection<IRoom> findAlternativeRooms(Date checkInDate, Date checkOutDate){
        return reservationService.findAlternativeRooms(checkInDate,checkOutDate);
    }

    public Date addDefaultPlusDays( Date date) {
        return reservationService.addDefaultPlusDays(date);
    }






}
