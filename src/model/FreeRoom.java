package model;

public class FreeRoom extends Room {


    public FreeRoom(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        //price= 0.0;

        super(roomNumber, 0.0, enumeration, isFree);
    }

    @Override


    public String toString() {
        {
            return ("\n  Room number: " + getRoomNumber() + " price  "
                    + getRoomPrice() + "  bed " + getEnumeration() + "  ");

        }

    }
}
