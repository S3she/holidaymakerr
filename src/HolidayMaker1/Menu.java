package HolidayMaker1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    DataSource ds;
    private int choice;
    String menuOption;

    public Menu() {
        scanner = new Scanner(System.in);
        ds = new DataSource();
        this.choice = choice;
        mainMenu();


    }

    public void mainMenu() {

        System.out.println("Welcome to the holiday booking system");
        System.out.println("");


        boolean runMenu = true;
        while (runMenu) {

            System.out.println("Main Menu");
            System.out.println();
            System.out.println("1. Create a guest");
            System.out.println("2. Search for a guest by last name");
            System.out.println("3. Make a reservation");
            System.out.println("4. Add guests to company");
            System.out.println("5. Show guests in a company");
            System.out.println("6. Cancel reservation");
            System.out.println("7. Manage facilities connected to hotels");
            System.out.println("8. Exit program");
            System.out.println("9. New reservation");


            choice = Integer.parseInt(scanner.nextLine());


            switch (choice) {

                case 1:
                    System.out.println("Guest created with ID = " + createGuest());

                    System.out.println("Do you want to add another guest? Press 1 for yes, 2 for no");
                    int selection = Integer.parseInt(scanner.nextLine());

                    if (selection == 1) {
                        createGuest();
                    } else if (selection >= 2) {
                        System.out.println("Going back to main menu");
                        Output.threadSleep();
                        Output.emptyScreen();
                        break;
                    }


                    break;

                case 2:
                    showGuestsByLastName();
                    break;

                case 3:
                    searchFreeRoomsAndBook();
                    break;

                case 4:
                    addGuestToCompany();
                    break;

                case 5:

                    System.out.println("Please enter a company ID");
                    int company_ID = Integer.parseInt(scanner.nextLine());
                    ds.getGuestBy(company_ID);
                    Output.pause();
                    Output.emptyScreen();
                    break;

                case 6:
                    cancelReservation();
                    System.out.println("Reservation cancelled");
                    System.out.println("Going back to main menu");
                    Output.threadSleep();
                    Output.emptyScreen();
                    break;


                case 7:
                    manageFacilities();
                    break;

                case 8:
                    System.out.println("Welcome back, exiting the program");
                    runMenu = false;
                    break;
                case 9:
                    searchFreeRoomsAndFacilitys();
                    break;

            }


        }

    }


    private void showAllGuest() {
        ArrayList<Guest> guests = ds.getAllGuests();
        for (Guest guest : guests) {

            System.out.println(guest);
        }
        Output.threadSleep();
    }

    private int createGuest() {
        String firstName;
        System.out.println("Please enter first name for the guest");
        firstName = scanner.nextLine();
        String lastName;
        System.out.println("Enter last name for the guest");
        lastName = scanner.nextLine();
        String phoneNumber;
        System.out.println("Enter the phone number for the guest");
        phoneNumber = scanner.nextLine();
        String emailAdress;
        System.out.println("Enter the email adress for the guest");
        emailAdress = scanner.nextLine();
        String dateOfBirth;
        System.out.println("Enter the birth date for the guest");
        dateOfBirth = scanner.nextLine();
        return ds.createGuest(new Guest(firstName, lastName, phoneNumber, emailAdress, dateOfBirth));
    }

    private void showAllHotels() {
        ArrayList<Hotel> hotels = ds.getAllHotels();
        for (Hotel hotel : hotels) {
            System.out.println(hotel);
        }


    }

    public void addPeopleToReservation() {
        System.out.println("Which reservation do you want to add to?");
        int reservationID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to include in the reservation?");
        int addGuestID = Integer.parseInt(scanner.nextLine());
        ds.addGuestToReservation(reservationID, addGuestID);
        System.out.println("Guest with ID " + addGuestID + " added to reservation with ID " + reservationID);
        System.out.println("Going back to main menu");
        Output.threadSleep();
        Output.emptyScreen();


    }

    public void addGuestToCompany() {
        System.out.println("Which company do you want to add to?");
        System.out.println("Press 1 to see a list of free company numbers or 2 to continue");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {

            ds.getCompanyID();
            addGuestToCompanyWithID();

        } else {

            addGuestToCompanyWithID();


        }
    }

    private void addGuestToCompanyWithID() {

        System.out.println("Enter the company ID");
        int company_ID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to include in the company?");
        int addGuestID = Integer.parseInt(scanner.nextLine());
        ds.addGuestToCompany(company_ID, addGuestID);
        System.out.println("Guest with ID " + addGuestID + " added to company with ID " + company_ID);
        System.out.println("Going back to main menu");
        Output.threadSleep();
        Output.emptyScreen();


    }


    public void addGuestToReservation() {

        System.out.println("Which reservation do you want to add the guest to?");
        int reservationID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to book?");
        int addGuestID = Integer.parseInt(scanner.nextLine());

        ds.insertGuestToReservation(addGuestID, reservationID);
        ds.addGuestToReservation(reservationID, addGuestID);

        System.out.println("Going back to main menu");
        mainMenu();


    }

    public void cancelReservation() {
        System.out.println("Which reservation do you want to cancel?");
        int reservationID = Integer.parseInt(scanner.nextLine());
        ds.cancelReservation(reservationID);
    }


    public void showGuestsByReservation() {

        System.out.println("Please enter a reservation id");
        int reservation_ID;
        reservation_ID = Integer.parseInt(scanner.nextLine());
        ArrayList<Guest> guests = ds.getGuestByReservation(reservation_ID);
        System.out.println("People in reservation with reservation ID " + reservation_ID + " :");
        for (Guest guest : guests) {
            System.out.println(guest);
        }

    }

    public void showGuestsByLastName() {

        System.out.println("Please enter a last name you want to search for");
        String last_Name = scanner.nextLine();

        ArrayList<Guest> guests = ds.getGuestByLastName(last_Name);
        System.out.println("People in reservation with last name " + last_Name + " :");
        for (Guest guest : guests) {
            System.out.println(guest);
            Output.threadSleep();
        }


        if (guests.size() == 0) {
            System.out.println("No guests with that last name");
            System.out.println("Going back to main menu");
            Output.threadSleep();
            Output.emptyScreen();
        }

    }


    public void searchFreeRoomsAndBook() {

        boolean runReservation = true;
        int bookHotel_ID;
        int bookRoomSize;
        int reservationChoice = 0;
        String check_In;
        String check_Out;


        while (runReservation) {


            System.out.println("Search for availible rooms and book them");
            System.out.println("Which Hotel do you want to search for rooms? Please enter Hotel ID");
            System.out.println("Here is a list of the hotels:");
            ArrayList<Hotel> hotels = ds.getAllHotels();
            for (Hotel hotel : hotels) {
                System.out.println(hotel);


            }

            bookHotel_ID = Integer.parseInt(scanner.nextLine());


            System.out.println("Which room size do you want to search for? Press 1 for Single room" +
                    "2 for a double room and 3 for a suite");
            bookRoomSize = Integer.parseInt(scanner.nextLine());
            System.out.println("When do you want to check in? Please enter a date in this format: year-month-day");
            System.out.println("Please remember, at the moment the hotels are only bookable between 220601 - 220731");
            check_In = scanner.nextLine();
            System.out.println("When do you want to check out? Please enter a date in this format: year-month-day");
            check_Out = scanner.nextLine();


            ArrayList<Room_Location> freeRoom = ds.getFreeRooms(check_Out, check_In, bookHotel_ID, bookRoomSize);

            for (Room_Location location : freeRoom) {
                System.out.println(location);

            }

            if (freeRoom.size() == 0) {
                System.out.println("No rooms free");
                System.out.println("Please do another search, going back to main menu");

            } else {

                System.out.println("Do you want to book a room? Press 1 for yes, 2 to go back to main menu");
                reservationChoice = Integer.parseInt(scanner.nextLine());

                switch (reservationChoice) {

                    case 1:

                        System.out.println("Room booked with reservation id  = " + bookRoom(check_In, check_Out, bookRoomSize, bookHotel_ID));
                        System.out.println("Do you want to add a guest to the reservation? Press 1 for yes or 2 to go back to the main menu");
                        reservationChoice = Integer.parseInt(scanner.nextLine());

                        if (reservationChoice == 1) {

                            addGuestToReservation();

                        } else if (reservationChoice == 2) {
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

    }

    public int bookRoom(String check_In, String check_Out, int bookRoomSize, int bookHotel_ID) {

        System.out.println("Which room number do you choose?");
        int room_Number = Integer.parseInt(scanner.nextLine());

        return ds.createReservation(new Reservation(check_In, check_Out, bookRoomSize, bookHotel_ID, room_Number));


    }

    public void manageFacilities() {

        boolean runManageFacilities = true;
        choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {

            case 1:
                System.out.println("Here is a list of the hotels: ");
                showAllHotels();
                System.out.println();
                System.out.println("Please enter a hotel id to see facilities");
                int hotel_ID = Integer.parseInt(scanner.nextLine());
                ds.getAllFacilitys(hotel_ID);
                System.out.println("Press 1 to add a facility or press 2 to go back to main menu");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    addFacility();

                } else if (choice == 2) {
                    Output.threadSleep();
                    Output.emptyScreen();
                    mainMenu();
                    break;
                }
            case 2:
                addFacility();
                break;

            default:
                break;

        }
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
        Output.threadSleep();
        Output.emptyScreen();


        return ds.createFacility(new Facility(hotel_ID, facilityName, facilityID));


    }


    public void searchFreeRoomsAndFacilitys() {

        boolean runReservation = true;
        int bookHotel_ID;
        int reservationChoice = 0;
        String check_In;
        String check_Out;
        int companyAmount;
        int choice;


        while (runReservation) {


            System.out.println("1. Search facilities belonging to a hotel");
            System.out.println("2. Add facilities to hotel");
            System.out.println("3. Search for availible rooms and book them");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:

                    System.out.println("Which hotel do you want to search for facilities?");
                    System.out.println("Here is a list of the hotels, please enter Hotel ID");
                    ArrayList<Hotel> hotels = ds.getAllHotels();
                    for (Hotel hotel : hotels) {
                        System.out.println(hotel);
                    }

                    choice = Integer.parseInt(scanner.nextLine());

                    ds.getAllFacilitys(choice);
                    System.out.println("Press 1 to go to booking or 2 to get back to main menu");
                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice == 1) {
                        bookRoomHotel();
                        break;

                    } else if (choice == 2) {
                        mainMenu();

                    }

                case 2:
                    addFacility();

                case 3:
                    bookRoomHotel();



            }

        }
    }





    public void bookRoomHotel() {


        int bookHotel_ID;
        int reservationChoice = 0;
        String check_In;
        String check_Out;
        int companyAmount;
        int choice;

        System.out.println("Which Hotel do you want to search for rooms? Please enter Hotel ID");
        System.out.println("Here is a list of the hotels:");
        ArrayList<Hotel> hotels = ds.getAllHotels();
        for (Hotel hotel : hotels) {
            System.out.println(hotel);

        }


        bookHotel_ID = Integer.parseInt(scanner.nextLine());

        System.out.println("When do you want to check in? Please enter a date in this format: year-month-day");
        System.out.println("Please remember, at the moment the hotels are only bookable between 220601 - 220731");
        check_In = scanner.nextLine();
        System.out.println("When do you want to check out? Please enter a date in this format: year-month-day");
        check_Out = scanner.nextLine();
        System.out.println("How many people are in the company?");
        companyAmount = Integer.parseInt(scanner.nextLine());


        ArrayList<Room_Location> freeRoom = ds.getFreeRooms2(check_Out, check_In, bookHotel_ID, companyAmount);

        for (Room_Location location : freeRoom) {
            System.out.println(location);

        }

        if (freeRoom.size() == 0) {
            System.out.println("No rooms free");
            System.out.println("Please do another search, going back to main menu");

        } else {

            System.out.println("Do you want to book a room? Press 1 for yes, 2 to go back to main menu");
            reservationChoice = Integer.parseInt(scanner.nextLine());

            switch (reservationChoice) {

                case 1:

                    System.out.println("Room booked with reservation id  = " + bookRoom(check_In, check_Out, bookHotel_ID, companyAmount));
                    System.out.println("Do you want to add a guest to the reservation? Press 1 for yes or 2 to go back to the main menu");
                    reservationChoice = Integer.parseInt(scanner.nextLine());

                    if (reservationChoice == 1) {

                        addGuestToReservation();

                    } else if (reservationChoice == 2) {
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

}
