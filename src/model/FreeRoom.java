package model;

public class FreeRoom extends Room {


    public FreeRoom(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        //price= 0.0;

        super(roomNumber, 0.0, enumeration, isFree);
    }

    @Override

    /*
    TO FIX: no point in checking if room is free, if freeRoom is called room is free.
    If no check for isFree though, toString prints that the room is available even if isFree is false
    check at runtime
     */
    public String toString() {
        if (isFree) {
            return ("\n  Room number: " + roomNumber + " price  "
                    + price + "  bed " + enumeration + "  ");
        } else
            return ("\n This room is not available.");
    }

}
