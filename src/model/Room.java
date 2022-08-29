package model;

public class Room implements IRoom{

    public String roomNumber;
    public Double price;
    RoomType enumeration;
    boolean isFree;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean isFree){
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.isFree = isFree;


    }
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString(){

            return ("\n Room number: "+ roomNumber + " is available. Price is: "
                    + price +" per night. This room is a "+ enumeration+ " one.");


    }

}
