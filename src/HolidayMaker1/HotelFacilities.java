package HolidayMaker1;

public class HotelFacilities {
    private String hotel_facilities_name;
    private int hotel_Facilities_ID;

    public HotelFacilities (String hotel_facilities_name, int hotel_Facilities_ID) {
        this.hotel_facilities_name = hotel_facilities_name;
        this.hotel_Facilities_ID = hotel_Facilities_ID;

    }


    @Override
    public String toString() {
        return "HotelFacilities{" +
                "hotel_facilities_name='" + hotel_facilities_name + '\'' +
                ", hotel_Facilities_ID=" + hotel_Facilities_ID +
                '}';
    }
}
