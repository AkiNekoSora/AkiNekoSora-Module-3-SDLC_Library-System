// Autumn Skye
// CEN-3024C 13950
// September 3rd, 2025

/*
 * The object of this project is to help a "local library" that allows their users to
 * add patrons using the Command Line Interface or a txt document or remove patrons,
 * display a list of all patrons within the system, manage the list of patrons(by updating
 * their information and their fine amount). All data is stored inside the system, not a
 * database.
 * The list of information the system holds for the patron is:
 *      - Patron ID (That is automatically generated)
 *      - First Name
 *      - Last Name
 *      - Street Address
 *      - Zip Code
 *      - State
 *      - Current Overdue Fine
 */

import java.util.*;


public class LibraryPatronSystem {
    /* Method Name: Main method
     * Purpose: Used to create the Patron Array List and start the system methods
     * Return Value: No return value
     */
    public static void main(String[] args) {
        List<Patrons> patronStorage = new ArrayList<>();

        userMenu(patronStorage);

    }

    /* Method Name: User Menu Method
     * Purpose: Used to start the system and have a while loop that loops until the user wants
     *          to exit the system. Asking for a number from the user every time.
     * Parameters: The List of patrons is passed to use to hold all Patrons needed for all
     *             methods in the library system
     * Return Value: Void/None
     */
    public static void userMenu(List<Patrons> patronStorage) {
        Scanner scnr = new Scanner(System.in);
        int userChoice = 1;

        System.out.println(Text.BLUE + "----------------------------------");
        System.out.println("| WELCOME TO THE LIBRARY SYSTEM! |");
        System.out.println("----------------------------------\n" + Text.RESET);

        /* A while loop used to accept the user input to decide what they would like to
         * do until they choose the "0" option to exit the system.
         */
        while (userChoice != 0) {
            LibraryPatronSystem.printLibraryMenuOptions();
            userChoice = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            // If else used to check if the user input is a valid number.
            if (userChoice >= 1 && userChoice <= 7) {
                libraryMenuOptions(userChoice, patronStorage);
            }
            else if (userChoice >= 8 || userChoice < 0) {
                System.out.print(Text.BRIGHT_RED + "Please enter a valid option between 0 and 7: " + Text.RESET);
                userChoice = scnr.nextInt();
                scnr.nextLine(); // Used to consume the newline
            }
        }
    }

    /* Method Name: Library Menu Options Method
     * Purpose: Takes in the option the user enters the User Menu Class and calls the method
     *          that matches the choice. Using a switch case to accept the number.
     * Parameters: The choice enter by the user in the UserMenu method. And the List of
     *             patrons to use with patron methods.
     * Return Value: Void/None
     */
    public static void libraryMenuOptions(int userChoice, List<Patrons> patronStorage) {
        // Switch for all Patron methods
        switch (userChoice) {
            case 1: Patrons.addPatron(patronStorage);
                    break;
            case 2: Patrons.loadPatronsFromFile(patronStorage);
                    break;
            case 3: Patrons.removePatronByID(patronStorage);
                    break;
            case 4: Patrons.findPatronByID(patronStorage);
                    break;
            case 5: Patrons.getAllPatrons(patronStorage);
                    break;
            case 6: Patrons.increaseFineAmount(patronStorage);
                    break;
            case 7: Patrons.decreaseFineAmount(patronStorage);
                    break;
        }

    }

    /* Method Name: Print Library Menu Options Method
     * Purpose: Used just to print out the menu and ask the user what number they would like to pick.
     * Parameters: None
     * Return Value: Void/None
     */
    public static void printLibraryMenuOptions() {
        String optionsMenu = """
               Library System options:
                    0. LEAVE LIBRARY SYSTEM
                    1. Add Patron with Command Line Interface
                    2. Add Patrons using a .txt file
                    3. Remove Patron with Command Line Interface (Using Patron ID)
                    4. Find Patron with Command Line Interface (Using Patron ID)
                    5. Print all Patrons to the Command Line Interface
                    6. Increase Fine Amount of a Patron (Using Patron ID)
                    7. Decrease Fine Amount of a Patron (Using Patron ID)
               """;

        System.out.println(Text.GREEN + "\n" + optionsMenu + "\n" + Text.RESET);
        System.out.print("What option would you like to pick: ");
    }

    /* Class Name: Text Class
     * Purpose: Used for the colors so that it can make it easier to understand
     *          the options and titles in the CMI.
     */
    public static class Text {
        public static final String RESET = "\u001B[0m";
        public static final String BRIGHT_RED = "\u001B[31;1m";
        public static final String GREEN = "\u001B[32;1m";
        public static final String BLUE = "\u001B[34;1m";
        public static final String MAGENTA = "\u001B[35m";
    }
}
