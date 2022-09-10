import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {

    private static String defaultDateFormat = "dd/MM/yyyy";
    private static HotelResource hotelResource = HotelResource.getInstance();

    public static void mainMenu(){

        String input ;
        Scanner scanner = new Scanner(System.in);
        printMainMenu();

        try {
            do {
                input = scanner.nextLine();

                if (input.length() == 1) {
                    switch (input.charAt(0)) {
                        case '1' :
                            findAndReserveRoom();
                            break;

                        case '2':
                        seeMyReservation();

                        case '3':
                            createAccount();
                            break;


                        case '4':
                            AdminMenu.adminMenu();
                            break;


                        case '5':
                            System.out.println("\nExit:");
                            break;

                        default :
                            System.out.println("\nInvalid Option");
                            break;
                    }

                    }else{
                    System.out.println("\nInvalid Choice");
                }
                //i while apo kato elegxei mono gia an !=5 na dokimaso ti ginetai genika
            }   while (input.charAt(0) != '5' || input.length() != 1 );
        }

        catch (StringIndexOutOfBoundsException ex){
            System.out.println("Empty Input received");
        }

    }

    /* OPTIONAL TODO: add code so user is not able to choose a date where:
        day>31 || day>29 if feb
        day<1
        month<1
        month>12
        date can not be in the past

     */
    private static void findAndReserveRoom(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter check in date. Format is:" + defaultDateFormat);
        Date checkInDate;
        checkInDate = getInputDate(scanner1);



        System.out.println("Enter check out date. Format is:"+ defaultDateFormat);
        Date checkOutDate = getInputDate(scanner1);

        if(checkInDate != null && checkOutDate != null)
        {
            Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate,checkOutDate);

            if (availableRooms.isEmpty()){
                Collection<IRoom> alternativeRooms = hotelResource.findAlternativeRooms(checkInDate,checkOutDate);

                if(alternativeRooms.isEmpty()){
                    System.out.println("\nThere are no rooms available.");
                    printMainMenu();
                }
                else {
                    Date alternativeCheckIn = hotelResource.addDefaultPlusDays(checkInDate);
                    Date alternativeCheckOut = hotelResource.addDefaultPlusDays(checkOutDate);
                    System.out.println("\nThere are no rooms on your selected dates. However there are rooms " +
                            "available on these dates: \n Check in: "+ alternativeCheckIn +" \n Check out: "+
                            alternativeCheckOut);
                    printRooms(alternativeRooms);
                    reserveRoom(scanner1, alternativeCheckIn, alternativeCheckOut, alternativeRooms);
                }


            } else {
                printRooms(availableRooms);
                reserveRoom(scanner1, checkInDate, checkOutDate , availableRooms);
            }
        }


    }


    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat(defaultDateFormat).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            findAndReserveRoom();
        }

        return null;
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<IRoom> rooms) {
        System.out.println("Would you like to book? y/n");
        final String choice = scanner.nextLine();

        if ("y".equals(choice)) {
            System.out.println("Do you have an account with us? y/n");
            final String hasAccount = scanner.nextLine();

            if ("y".equals(hasAccount)) {
                System.out.println("Enter Email format: name@domain.com");
                final String customerEmail = scanner.nextLine();

                if (hotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Customer not found.\nYou need to create a new account.");
                } else {
                    System.out.println("What room number would you like to reserve?");
                    final String roomNumber = scanner.nextLine();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        final IRoom room = hotelResource.getRoom(roomNumber);

                        final Reservation reservation = hotelResource
                                .bookARoom(customerEmail, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: room number not available.\nStart reservation again.");
                    }
                }

                printMainMenu();
            } else {
                System.out.println("Please, create an account.");
                printMainMenu();
            }
        } else if ("n".equals(choice)){
            printMainMenu();
        } else {
            reserveRoom(scanner, checkInDate, checkOutDate, rooms);
        }
    }

    private static void printRooms(final Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    /*
    FIXED: seeMyReservation throws a NullPointerException when the user searches for an email but a customer with that email
     has not been created yet
     */
    private static void seeMyReservation() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email format: name@domain.com");
        final String customerEmail = scanner.nextLine();

        try{
            printReservations(hotelResource.getCustomersReservations(customerEmail));
        }
        catch (NullPointerException ex)
        {
            System.out.println("There is no customer with this email.");
            printMainMenu();
        }


    }

    private static void printReservations(final Collection<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(reservation -> System.out.println("\n" + reservation));
        }
    }

    private static void createAccount() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Email format: name@domain.com");
        final String email = scanner.nextLine();

        System.out.println("First Name:");
        final String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        final String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer( firstName, lastName, email);
            System.out.println("Account created successfully!");

            printMainMenu();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }







    public static void printMainMenu(){
        System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }


}



