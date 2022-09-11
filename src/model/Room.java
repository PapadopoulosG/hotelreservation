package model;

import java.util.Objects;

public class Room implements IRoom{

    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;
    private final boolean isFree;

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
    public String toString() {
        return "Number: " + this.roomNumber
                + " Price: $" + this.price
                + " Enumeration: " + this.enumeration;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Room)) {
            return false;
        }

        final Room room = (Room) obj;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    public RoomType getEnumeration() {
        return enumeration;
    }
}
