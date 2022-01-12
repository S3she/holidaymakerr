package HolidayMaker1;

public class Hotel {

    private String hotel_Name;
    private int hotel_ID;
    private String hotel_Address;
    private String hotel_Phone_Number;
    private String hotel_City;
    private String hotel_Country;
    private String hotel_Email;
    private String hotel_Zip_Code;

    public Hotel (String hotel_Name, String hotel_City, String hotel_Country) {
        this.hotel_Name = hotel_Name;
        this.hotel_City = hotel_City;
        this.hotel_Country = hotel_Country;
    }

    public Hotel(String hotel_Name, int hotel_ID, String hotel_Address, String hotel_Phone_Number, String hotel_City, String hotel_Country, String hotel_Email, String hotel_Zip_Code) {
        this.hotel_Name = hotel_Name;
        this.hotel_ID = hotel_ID;
        this.hotel_Address = hotel_Address;
        this.hotel_Phone_Number = hotel_Phone_Number;
        this.hotel_City = hotel_City;
        this.hotel_Country = hotel_Country;
        this.hotel_Email = hotel_Email;
        this.hotel_Zip_Code = hotel_Zip_Code;
    }


    public String toString() {
        return "Name: " + hotel_Name + " ,  " + "Hotel ID: " + hotel_ID + " ,  " + "Hotel Adress: " + hotel_Address + " ,  " +  "City: " + hotel_City +   " ,  " + "Country: " + hotel_Country;
    }

}
