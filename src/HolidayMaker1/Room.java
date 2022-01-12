package HolidayMaker1;

public class Room {

    private int room_ID;
    private String room_Type;
    private int room_Capacity;
    private int room_Amount;

    public Room(int room_ID, String room_Type, int room_Capacity, int room_Amount) {
        this.room_ID = room_ID;
        this.room_Type = room_Type;
        this.room_Capacity = room_Capacity;
        this.room_Amount = room_Amount;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_ID=" + room_ID +
                ", room_Type='" + room_Type + '\'' +
                ", room_Capacity=" + room_Capacity +
                ", room_Amount=" + room_Amount +
                '}';
    }
}
