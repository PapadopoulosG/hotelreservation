package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static ReservationService INSTANCE;
    private final ReservationService reservationService = ReservationService.getInstance();
    private Map<String, IRoom> roomMap = new HashMap<>();
    private Map<String, Collection<Reservation>> reservationMap = new HashMap<>();

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }


    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomNumber) {
        return (roomMap.get(roomNumber));
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = getCustomerReservation(customer);

        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }
        customerReservations.add(reservation);
        reservationMap.put(customer.getEmail(), customerReservations);

        return reservation;

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        /*
        an grapso mia getAllRooms, mporo na kano populate ena Collection<Iroom> of available rooms
         kai meta if reservationOverlaps -> availableRooms.remove (reservation.getRoom())
         kai return availableRooms
         */

        Collection<Reservation> allReservations = getAllReservations();
        Collection<IRoom> unAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservations) {
            if (reservationOverlaps(reservation, checkInDate, checkOutDate)) {
                //na do an if(!reservation.room.isFree()); paragei allo apotelesma
                unAvailableRooms.add(reservation.getRoom());
            }
        }


        return roomMap.values().stream().filter(room -> unAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }

    public void printAllReservation() {
        Collection<Reservation> reservations = getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations.");
        } else {
           // na dokimaso System.out.println("\n Here are all the reservations: " + reservationMap.values());
            for (Reservation reservation : reservations) {
                System.out.println("\n " + reservation);
            }
        }


    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        return reservationMap.get(customer.getEmail());
    }

    public Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new LinkedList<>();
        for (Collection<Reservation> reservations : reservationMap.values()) {
            allReservations.addAll(reservations);
        }
        return allReservations;
    }

    public boolean reservationOverlaps(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }


}
