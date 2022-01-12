package HolidayMaker1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    Database db;
    private int option;


    public Menu() {
        scanner = new Scanner(System.in);
        db = new Database();
        this.option = option;
        mainMenu();

    }

    private void mainMenu() {

        boolean runMenu = true;
        while (runMenu) {

            System.out.println("Main Menu");
            System.out.println();
            System.out.println("WELCOME TO THE HOTEL MANAGEMENT BOOKING SYSTEM");
            System.out.println("");
            System.out.println("1. Do you want to - Create a new guest?");
            System.out.println("2. Do you want to - Search for a guest by their last name?");
            System.out.println("3. Do you want to - Make a reservation / See facilities?");
            System.out.println("4. Do you want to - Add guests to a Group?");
            System.out.println("5. Do you want to - See guests in a Group?");
            System.out.println("6. Do you want to - Cancel an reservation?");
            System.out.println("7. Do you want to - Exit the program?");


            option = Integer.parseInt(scanner.nextLine());


            switch (option) {

                case 1:
                    System.out.println("Guest created with ID = " + createGuest());
                    System.out.println(" returning to the main menu");
                    Extras.threadSleep();
                    Extras.emptyScreen();
                    break;

                case 2:

                    showGuestsByLastName();
                    break;

                case 3:
                    searchFreeRoomsAndFacilitys();
                    break;

                case 4:
                    addGuestToGroup();
                    break;

                case 5:

                    System.out.println("Please enter a Group ID");
                    int group_ID = Integer.parseInt(scanner.nextLine());
                    db.getGuestByGroup_ID(group_ID);
                    Extras.pause();
                    Extras.emptyScreen();
                    break;

                case 6:
                    cancelReservation();
                    break;

                case 7:
                    System.out.println("Exiting the program");
                    runMenu = false;
                    break;

                default:
                    break;

            }


        }

    }

    private void showAllHotels() {
        ArrayList<Hotel> hotels = db.getAllHotels();
        for (Hotel hotel : hotels) {
            System.out.println(hotel);
        }


    }

    private void addGuestToGroup() {
        System.out.println("Which Group do you want to add to?");
        System.out.println("Press 1 to see a the next free Group id or press 2 to continue to add");
        System.out.println("Press 3 to go back to main menu");
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {

            db.getGroupID();
            Extras.threadSleep();
            Extras.emptyScreen();

            addGuestToGroup();

        } else if (option == 2) {

            addGuestToGroupWithID();
        }

        else {
            mainMenu();
        }
    }

    private void addGuestToGroupWithID() {

        System.out.println("Enter the Group ID");
        int group_ID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to include in the Group?");
        int addGuestID = Integer.parseInt(scanner.nextLine());
        db.addGuestToGroup(group_ID, addGuestID);
        System.out.println("Guest with ID " + addGuestID + " added to Group with ID " + group_ID);
        System.out.println("Going back to main menu");
        Extras.threadSleep();
        Extras.emptyScreen();


    }


    private void addGuestToReservation() {

        System.out.println("Which reservation do you want to add the guest to?");
        int reservationID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to book? insert Guest ID");
        int addGuestID = Integer.parseInt(scanner.nextLine());

        db.insertGuestToReservation(addGuestID, reservationID);
        db.addGuestToReservation(reservationID, addGuestID);

        System.out.println("Guest added");
        System.out.println("Going back to main menu");
        Extras.threadSleep();
        Extras.emptyScreen();
        mainMenu();

    }

    private void cancelReservation() {

        System.out.println("Press 1 to cancel a reservation with reservation id");
        System.out.println("Press 2 to search for a reservation with guest last name");
        int cancelReservation = Integer.parseInt(scanner.nextLine());

        switch (cancelReservation) {

            case 1:
                System.out.println("Enter a reservation id");
                int reservationID = Integer.parseInt(scanner.nextLine());
                db.cancelReservation(reservationID);
                System.out.println("Reservation with ID " + reservationID + " is now cancelled");
                System.out.println("Going back to main menu");
                Extras.threadSleep();
                Extras.emptyScreen();
                mainMenu();
                break;


            case 2:
                System.out.println("Enter last name to search for");
                String last_Name = scanner.nextLine();
                db.getAllReservations(last_Name);

                System.out.println("Press 1 to go back or press 2 to go back to main menu");
                cancelReservation = Integer.parseInt(scanner.nextLine());

                if (cancelReservation == 1) {
                    cancelReservation();
                }
                else if (cancelReservation == 2) {
                    mainMenu();
                }

                break;

        }

    }

    private void showGuestsByLastName() {

        System.out.println("Enter the last name you want to search for");
        String last_Name = scanner.nextLine();

        ArrayList<Guest> guests = db.getGuestByLastName(last_Name);
        System.out.println("People in reservation with last name " + last_Name + " :");
        for (Guest guest : guests) {
            System.out.println(guest);
            Extras.threadSleep();
        }


        if (guests.size() == 0) {
            System.out.println("No guests with that last name");
            System.out.println("Going back to main menu");
            Extras.threadSleep();
            Extras.emptyScreen();
            mainMenu();
        }

    }


    private int bookRoom(String check_In, String check_Out, int bookRoomSize, int bookHotel_ID) {

        System.out.println("Which room number are you interested in?");
        int room_Number = Integer.parseInt(scanner.nextLine());

        return db.createReservation(new Reservation(check_In, check_Out, bookRoomSize, bookHotel_ID, room_Number));

    }

    private int addFacility() {

        System.out.println("Please enter the hotel ID");
        int hotel_ID = Integer.parseInt(scanner.nextLine());

        System.out.println("The name of the facility");
        String facilityName = scanner.nextLine();

        System.out.println("What type of facility is it?");
        System.out.println("Press 1 for pool, 2 for evening entertainment, 3 for restaurant or 4 for childrens club");

        int facilityID = Integer.parseInt(scanner.nextLine());
        System.out.println("Facility added!");
        System.out.println("Going back to Main menu");
        Extras.threadSleep();
        Extras.emptyScreen();

        return db.createFacility(new Facility(hotel_ID, facilityName, facilityID));


    }


    private void searchFreeRoomsAndFacilitys() {

        boolean runReservation = true;
        int bookHotel_ID;
        int reservationChoice = 0;
        String check_In;
        String check_Out;
        int companyAmount;
        int option;


        while (runReservation) {


            System.out.println("1. Do you want to - Search facilities belonging to a specific hotel?");
            System.out.println("2. Do you want to - Add specific facilities to a hotel?");
            System.out.println("3. Do you want to - Search for guests in a Group?");
            System.out.println("4. Do you want to - Search for available rooms and book a room?");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {

                case 1:

                    System.out.println("Which is the hotel do you want to see available facilities in?");
                    System.out.println("Here is a list of all the hotels, please enter Hotel ID");
                    ArrayList<Hotel> hotels = db.getAllHotels();
                    for (Hotel hotel : hotels) {
                        System.out.println(hotel);
                    }

                    option = Integer.parseInt(scanner.nextLine());

                    db.getAllFacilitys(option);
                    System.out.println("Press 1 to go to booking 2 to add facilities or 3 to get back to main menu");
                    option = Integer.parseInt(scanner.nextLine());

                    if (option == 1) {
                        Extras.emptyScreen();
                        bookHotelRoom();
                        break;

                    } else if (option == 2) {
                        addFacility();
                        mainMenu();
                        break;

                    }
                    else {
                        mainMenu();
                    }

                case 2:
                    addFacility();
                    mainMenu();
                    break;

                case 3:
                    System.out.println("Enter a Group ID");
                    int group_ID = Integer.parseInt(scanner.nextLine());
                    db.getGuestByGroup_ID(group_ID);
                    Extras.threadSleep();
                    break;

                case 4:
                    bookHotelRoom();

            }

        }
    }


    private void bookHotelRoom() {


        int bookHotel_ID;
        int reservationChoice = 0;
        String check_In;
        String check_Out;
        int guestAmount;
        int option;

        System.out.println("Please enter Hotel ID to search for available rooms");
        System.out.println("");

        System.out.println("Here is a list of the available hotels:");
        ArrayList<Hotel> hotels = db.getAllHotels();
        for (Hotel hotel : hotels) {
            System.out.println(hotel);

        }

        bookHotel_ID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter a date for CHECK IN in this format: YY-MM-DD");
        System.out.println("The rooms are bookable: 220601 - 220731");
        check_In = scanner.nextLine();
        System.out.println(" Enter a date for CHECK OUT in this format: YY-MM-DD ");
        check_Out = scanner.nextLine();
        System.out.println("How many people is booking?");
        guestAmount = Integer.parseInt(scanner.nextLine());

        ArrayList<Room_Location> freeRoom = db.getFreeRooms(check_Out, check_In, bookHotel_ID, guestAmount);

        System.out.println("Here is a list of free rooms with capacity to house the Group of choice");

        for (Room_Location location : freeRoom) {
            System.out.println(location);

        }

        if (freeRoom.size() == 0) {
            System.out.println("No free rooms available");
            System.out.println("Please do another search");
            Extras.threadSleep();
            Extras.emptyScreen();
            mainMenu();

        } else {

            System.out.println("Do you want to book a room? Press 1 for yes, 2 to go back to main menu");
            reservationChoice = Integer.parseInt(scanner.nextLine());

            switch (reservationChoice) {

                case 1:

                    System.out.println("Room booked with reservation id " + bookRoom(check_In, check_Out, bookHotel_ID, guestAmount));
                    System.out.println("Which guest will be the contact for the reservation?");
                    System.out.println("Press 1 to add guest 2. Search for a guest by last name 3. Go back to main menu");
                    reservationChoice = Integer.parseInt(scanner.nextLine());

                    if (reservationChoice == 1) {

                        addGuestToReservation();


                    } else if (reservationChoice == 2) {

                        searchGuestFromReservation();
                        System.out.println("Press 1 to continue adding the guest or 2 to do a new search");
                        option = Integer.parseInt(scanner.nextLine());

                        if (option == 1) {
                            addGuestToReservation();

                        }
                        else if (option == 2) {
                            searchGuestFromReservation();
                        }

                    } else {
                        mainMenu();
                    }

                    break;

                case 2:
                    mainMenu();
                    break;

                default:
                    break;

            }


        }
    }

    private int createGuest() {


        String firstName;
        System.out.println("Please enter first name of the guest");
        firstName = scanner.nextLine();
        String lastName;
        System.out.println("Enter last name of the guest");
        lastName = scanner.nextLine();
        String phoneNumber;
        System.out.println("Enter the phone number of the guest");
        phoneNumber = scanner.nextLine();
        String emailAdress;
        System.out.println("Enter the email adress of the guest");
        emailAdress = scanner.nextLine();
        String dateOfBirth;
        System.out.println("Enter the birth date of the guest");
        dateOfBirth = scanner.nextLine();
        System.out.println("Guest is successfully added!");
        return db.createGuest(new Guest(firstName, lastName, phoneNumber, emailAdress, dateOfBirth));
    }

    public void searchGuestFromReservation() {

        System.out.println("Enter the guests last name you want to search for");
        String last_Name = scanner.nextLine();
        ArrayList<Guest> guests = db.getGuestByLastName(last_Name);
        System.out.println("People with last name " + last_Name + " :");
        for (Guest guest : guests) {
            System.out.println(guest);

        }
        if (guests.size() == 0) {
            System.out.println("No Guest Was found.");
        }




    }
}
