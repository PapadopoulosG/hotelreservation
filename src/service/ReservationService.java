package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static final ReservationService INSTANCE = new ReservationService();
    private final ReservationService reservationService = ReservationService.getInstance();
    private final Map<String, IRoom> roomMap = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservationMap = new HashMap<>();

    private static final int addDayToFindAlternativeRoom = 7;

    private ReservationService(){}
    public static ReservationService getInstance() {

        return INSTANCE;
    }


    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomNumber) {
        return (roomMap.get(roomNumber));
    }

    public Collection<IRoom> getAllRooms() {
        return roomMap.values();
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

    private boolean reservationOverlaps(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    public Date addDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addDayToFindAlternativeRoom);
        return calendar.getTime();
    }

    public Collection<IRoom> findAlternativeRooms(final Date checkInDate, final Date checkOutDate) {
        return findRooms(addDays(checkInDate), addDays(checkOutDate));
    }

}
