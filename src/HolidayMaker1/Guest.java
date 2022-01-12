package HolidayMaker1;

public class Guest {

    private String first_Name;
    private String last_Name;
    private String phoneNumber;
    private String email_Adress;
    private String date_Of_Birth;
    private int guest_ID;
    private int reservation_ID;
    private int group_ID;



    public Guest (String first_Name, String last_Name, String phoneNumber, String email_Adress, String date_Of_Birth,
                  int guest_ID, int reservation_ID, int group_ID) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phoneNumber = phoneNumber;
        this.email_Adress = email_Adress;
        this.date_Of_Birth = date_Of_Birth;
        this.guest_ID = guest_ID;
        this.reservation_ID = reservation_ID;
        this.group_ID = group_ID;

    }

    public Guest (String first_Name, String last_Name, String phoneNumber, String email_Adress, String date_Of_Birth) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phoneNumber = phoneNumber;
        this.email_Adress = email_Adress;
        this.date_Of_Birth = date_Of_Birth;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail_Adress() {
        return email_Adress;
    }

    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }


    @Override
    public String toString() {
        return "First name: " + first_Name + " " + "Last name: " + last_Name + " " + "Phone number: " + phoneNumber + " " + "Email adress: " + email_Adress + " " + "Date of birth: "+ date_Of_Birth +
                " " + "Guest ID: " + guest_ID + " " + "Reservation ID: " + reservation_ID + "Company ID " + group_ID;
    }
}
