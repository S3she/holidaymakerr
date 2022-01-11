package HolidayMaker1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    Database ds;
    private int choice;
    String menuOption;

    public Menu() {
        scanner = new Scanner(System.in);
        ds = new Database();
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
            System.out.println("3. Make a reservation / See facilites");
            System.out.println("4. Add guests to company");
            System.out.println("5. Show guests in a company");
            System.out.println("6. Cancel reservation");
            System.out.println("7. Exit program");


            choice = Integer.parseInt(scanner.nextLine());


            switch (choice) {

                case 1:
                    System.out.println("Guest created with ID = " + createGuest());
                    System.out.println("Going back to main menu");
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
                    addGuestToCompany();
                    break;

                case 5:

                    System.out.println("Please enter a company ID");
                    int company_ID = Integer.parseInt(scanner.nextLine());
                    ds.getGuestByCompany_ID(company_ID);
                    Extras.pause();
                    Extras.emptyScreen();
                    break;

                case 6:
                    cancelReservation();
                    break;

                case 7:
                    System.out.println("Welcome back, exiting the program");
                    runMenu = false;
                    break;

            }


        }

    }


    private void showAllGuest() {
        ArrayList<Guest> guests = ds.getAllGuests();
        for (Guest guest : guests) {

            System.out.println(guest);
        }
        Extras.threadSleep();
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
        Extras.threadSleep();
        Extras.emptyScreen();


    }

    public void addGuestToCompany() {
        System.out.println("Which company do you want to add to?");
        System.out.println("Press 1 to see a the next free company id or press 2 to continue to add");
        System.out.println("Press 3 to go back to main menu");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {

            ds.getCompanyID();
            Extras.threadSleep();
            Extras.emptyScreen();

            addGuestToCompany();

        } else if (choice == 2) {

            addGuestToCompanyWithID();


        }

        else {
            mainMenu();
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
        Extras.threadSleep();
        Extras.emptyScreen();


    }


    public void addGuestToReservation() {

        System.out.println("Which reservation do you want to add the guest to?");
        int reservationID = Integer.parseInt(scanner.nextLine());
        System.out.println("Which guest do you want to book?");
        int addGuestID = Integer.parseInt(scanner.nextLine());

        ds.insertGuestToReservation(addGuestID, reservationID);
        ds.addGuestToReservation(reservationID, addGuestID);

        System.out.println("Guest added");
        System.out.println("Going back to main menu");
        Extras.threadSleep();
        Extras.emptyScreen();
        mainMenu();


    }

    public void cancelReservation() {

        System.out.println("Press 1 to cancel a reservation with reservation id");
        System.out.println("Press 2 to search for a reservation with guest last name");
        int cancelReservation = Integer.parseInt(scanner.nextLine());

        switch (cancelReservation) {

            case 1:
                System.out.println("Enter a reservation id");
                int reservationID = Integer.parseInt(scanner.nextLine());
                ds.cancelReservation(reservationID);
                System.out.println("Reservation with ID " + reservationID + " is now cancelled");
                System.out.println("Going back to main menu");
                Extras.threadSleep();
                Extras.emptyScreen();
                mainMenu();
                break;


            case 2:
                System.out.println("Enter last name to search for");
                String last_Name = scanner.nextLine();
                ds.getAllReservations(last_Name);

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
            Extras.threadSleep();
        }


        if (guests.size() == 0) {
            System.out.println("No guests with that last name");
            System.out.println("Going back to main menu");
            Extras.threadSleep();
            Extras.emptyScreen();
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
                    mainMenu();
                    break;

                } else if (choice == 2) {
                    Extras.threadSleep();
                    Extras.emptyScreen();
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
        Extras.threadSleep();
        Extras.emptyScreen();

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
            System.out.println("3. Search for a company or single guest");
            System.out.println("4. Search for availible rooms and book them");
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
                    System.out.println("Press 1 to go to booking 2 to add facility(s) or 3 to get back to main menu");
                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice == 1) {
                        Extras.emptyScreen();
                        bookRoomHotel();
                        break;

                    } else if (choice == 2) {
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
                    System.out.println("Enter a company ID");
                    int company_ID = Integer.parseInt(scanner.nextLine());
                    ds.getGuestByCompany_ID(company_ID);
                    Extras.threadSleep();
                    break;

                case 4:
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
        System.out.println("");

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


        ArrayList<Room_Location> freeRoom = ds.getFreeRooms(check_Out, check_In, bookHotel_ID, companyAmount);

        for (Room_Location location : freeRoom) {
            System.out.println(location);

        }

        if (freeRoom.size() == 0) {
            System.out.println("No free rooms");
            System.out.println("Please do another search");
            Extras.threadSleep();
            Extras.emptyScreen();
            mainMenu();

        } else {

            System.out.println("Do you want to book a room? Press 1 for yes, 2 to go back to main menu");
            reservationChoice = Integer.parseInt(scanner.nextLine());

            switch (reservationChoice) {

                case 1:

                    System.out.println("Room booked with reservation id " + bookRoom(check_In, check_Out, bookHotel_ID, companyAmount));
                    System.out.println("Which guest will be the contact for the reservation?");
                    System.out.println("Press 1 to add guest 2. Search for a guest by last name 3. See guests in company 4. Go back to main menu");
                    reservationChoice = Integer.parseInt(scanner.nextLine());

                    if (reservationChoice == 1) {

                        addGuestToReservation();


                    } else if (reservationChoice == 2) {
                        System.out.println("Please enter a last name you want to search for");
                        String last_Name = scanner.nextLine();

                        ArrayList<Guest> guests = ds.getGuestByLastName(last_Name);
                        System.out.println("People with last name " + last_Name + " :");
                        for (Guest guest : guests) {
                            System.out.println(guest);

                        }

                        addGuestToReservation();


                    } else if (reservationChoice == 3) {

                        System.out.println("Enter a company ID");
                        int company_ID = Integer.parseInt(scanner.nextLine());
                        ds.getGuestByCompany_ID(company_ID);
                        addGuestToReservation();
                        System.out.println("Guest added");
                        System.out.println("Going back to main menu");
                        Extras.threadSleep();
                        Extras.emptyScreen();
                        mainMenu();


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

}
