package HolidayMaker1;

public class Reservation {

    private int reservation_ID;
    private String check_In;
    private String check_Out;
    private int room_ID;
    private int hotel_ID;
    private int guest_ID;
    private int room_Number;

    public Reservation(int reservation_ID, String check_In, String check_Out, int room_ID, int hotel_ID, int guest_ID, int room_Number) {
        this.reservation_ID = reservation_ID;
        this.check_In = check_In;
        this.check_Out = check_Out;
        this.room_ID = room_ID;
        this.hotel_ID = hotel_ID;
        this.guest_ID = guest_ID;
        this.room_Number = room_Number;
    }

    public Reservation(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public Reservation(String check_In, String check_Out, int room_ID, int hotel_ID, int room_Number) {

        this.check_In = check_In;
        this.check_Out = check_Out;
        this.room_ID = room_ID;
        this.hotel_ID = hotel_ID;
        this.room_Number = room_Number;

    }


    public int getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public String getCheck_In() {
        return check_In;
    }

    public void setCheck_In(String check_In) {
        this.check_In = check_In;
    }

    public String getCheck_Out() {
        return check_Out;
    }

    public void setCheck_Out(String check_Out) {
        this.check_Out = check_Out;
    }

    public int getRoom_ID() {
        return room_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }

    public int getHotel_ID() {
        return hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        this.hotel_ID = hotel_ID;
    }

    public int getGuest_ID() {
        return guest_ID;
    }

    public void setGuest_ID(int guest_ID) {
        this.guest_ID = guest_ID;
    }

    public int getRoom_Number() {
        return room_Number;
    }

    public void setRoom_Number(int room_Number) {
        this.room_Number = room_Number;
    }

    @Override
    public String toString() {

        return "Reservation_ID: = " + reservation_ID + " ," + " Check_In: " + check_In + " ," + "Check_Out: " + check_Out + " , " + "Room ID: " +
                room_ID + " , " + "Hotel ID: " + hotel_ID + " , " + "Guest ID " + guest_ID + " , " + "Room number: " + room_Number;

    }

}