import java.util.*;
import java.io.*;

/*
 * Autumn Skye
 * CEN-3024C 13950
 * September 3rd, 2025
 *
 * Class Name: Patrons Class
 * Purpose: Used to create new patrons.
 *          Includes methods that allows the user to:
 *              - Add a patron using the CMI
 *              - Remove a patron using a Patron ID
 *              - Find a patron using a Patron ID
 *              - Get all patrons and prints them to the CMI
 *              - Increase Fine amount
 *              - Decrease Fine amount
 *              - Add patrons using a txt document (Where each item is seperated by a "-"
 */
public class Patrons {
    static Scanner scnr = new Scanner(System.in);

    //Patron Variables
    private static int nextID = 1000000; //Incremented number
    private int patronID; // Used the incremental number nextID.
    private String patronFName;
    private String patronLName;
    private String patronStreetAddress;
    private String patronCity;
    private String patronState;
    private String patronZipCode;
    private Double patronOverdueFine;

    //Constructor for the Patrons Class!
    public Patrons(String patronFName, String patronLName, String streetAddress,
                   String city, String state, String zipCode) {
        this.patronFName = patronFName;
        this.patronLName = patronLName;
        this.patronStreetAddress = streetAddress;
        this.patronCity = city;
        this.patronState = state;
        this.patronZipCode = zipCode;
        this.patronOverdueFine = 0.00;
        // This is used to auto increment the "nextID" int above.
        this.patronID = nextID++;
    }

    //Getters!
    public int getPatronID() {return patronID;}
    public String getPatronFName() {return patronFName;}
    public String getPatronLName() {return patronLName;}
    public String getStreetAddress() {return patronStreetAddress;}
    public String getCity() {return patronCity;}
    public String getState() {return patronState;}
    public String getZipCode() {return patronZipCode;}
    public Double getOverdueFine() {return patronOverdueFine;}

    //Setters!
    public void setPatronID(int patronID) {this.patronID = patronID;}
    public void setPatronFName(String patronFName) {this.patronFName = patronFName;}
    public void setPatronLName(String patronLName) {this.patronLName = patronLName;}
    public void setStreetAddress(String streetAddress) {this.patronStreetAddress = streetAddress;}
    public void setCity(String city) {this.patronCity = city;}
    public void setState(String state) {this.patronState = state;}
    public void setZipCode(String zipCode) {this.patronZipCode = zipCode;}
    public void setOverdueFine(Double newFine) throws FineLimitExceededException {
        // Makes sure that the fine does not go over 250 per requirement listed.
        if ((newFine + this.patronOverdueFine) > 250) {
            throw new FineLimitExceededException("Fine exceeds $250.00. Patron is no longer allowed to rent from this library.");
        } else {
            this.patronOverdueFine = newFine;
        }
    }

    /* Method Name: Add Patron Method
     * Purpose: Lets the user create a new Patron using the CMI
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void addPatron(List<Patrons> patronStorage) {
        System.out.println(LibraryPatronSystem.Text.BLUE + "\nCREATING A NEW PATRON!");
        System.out.println("----------------------\n" + LibraryPatronSystem.Text.RESET);

        System.out.print("Enter Patron First Name: ");
        String patronFName = scnr.nextLine();
        System.out.print("Enter Patron Last Name: ");
        String patronLName = scnr.nextLine();
        System.out.print("Enter Patron Street Address: ");
        String patronStreetAddress = scnr.nextLine();
        System.out.print("Enter Patron City: ");
        String patronCity = scnr.nextLine();
        System.out.print("Enter Patron State (Please use full State name): ");
        String patronState = scnr.nextLine();
        System.out.print("Enter Patron 5-Digit Zip Code: ");
        String patronZipCode = scnr.nextLine();
        // Checks to make sure it is a valid Zip Code
        while (patronZipCode.length() > 5) {
            patronZipCode = patronZipCode.substring(0, 5);

            System.out.println("The number entered was longer than 5 characters.");
            System.out.print("Is this Zip Code correct? (y or n) " + patronZipCode + ": ");
            String userAnswer = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));

            if (userAnswer.equals("n")) {
                System.out.print("Please enter the correct 5-Digit Zip Code: ");
                patronZipCode = scnr.nextLine();
            }
        }

        String userAnswer = "n";
        // Allows the user to check if all information is Valid and change it if it is not.
        while (userAnswer.equals("n")){
            System.out.println("\nPlease verify the Patron information below.");
            System.out.println("Patron Full Name:    " + patronFName + " " + patronLName + "\n" +
                    "Patron Address:      " + patronStreetAddress + "\n                     " +
                    patronCity + ", " + patronState + " " + patronZipCode);

            System.out.print("Is all of this information correct? (y or n): ");
            userAnswer = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));
            if (userAnswer.equals("y")) {
                break;
            }
            System.out.println("What is the information you would like to fix?");
            String answers = """
                        1. Patron First Name
                        2. Patron Last Name
                        3. Patron Street Address
                        4. Patron City
                        5. Patron State
                        6. Patron Zip Code
                    """;
            System.out.print(answers);
            System.out.print("Please enter a number between 1 and 6: ");
            int userNumber = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            // Checks if the user entered number is valid
            if (userNumber < 1 || userNumber > 6) {
                System.out.println(userNumber + " is not a valid number.");
                System.out.print("Please enter a number between 1 and 6: ");
                userNumber = scnr.nextInt();
                scnr.nextLine(); // Used to consume the newline
            }

            // Allows the user to re-enter the correct information
            if (userNumber == 1) {
                System.out.print("Please re-enter the Patron First Name: ");
                patronFName = scnr.nextLine();
            } if (userNumber == 2) {
                System.out.print("Please re-enter the Patron Last Name: ");
                patronLName = scnr.nextLine();
            } if (userNumber == 3) {
                System.out.print("Please re-enter the Patron Street Address: ");
                patronStreetAddress = scnr.nextLine();
            } if (userNumber == 4) {
                System.out.print("Please re-enter the Patron City: ");
                patronCity = scnr.nextLine();
            } if (userNumber == 5) {
                System.out.print("Please re-enter the Patron State: ");
                patronState = scnr.nextLine();
            } if (userNumber == 6) {
                System.out.print("Please re-enter the Patron Zip Code: ");
                patronZipCode = scnr.nextLine();
            }
        }

        Patrons newPatron = new Patrons (patronFName, patronLName, patronStreetAddress,
                patronCity, patronState, patronZipCode);

        patronStorage.add(newPatron);

        System.out.println(LibraryPatronSystem.Text.MAGENTA + "\nPatron added successfully!");
        System.out.println("Please see new Patron Information below.");
        System.out.println("(Make sure to write down the Patron ID for the Patron.)");
        System.out.println(patronStorage.get(patronStorage.size() - 1) + LibraryPatronSystem.Text.RESET + "\n");

    }

    /* Method Name: Remove Patron By ID Method
     * Purpose: Allows a user to remove a Patron using a Patron ID
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void removePatronByID(List<Patrons> patronStorage) {
        boolean patronFound = false;
        String userTryAgain = "y";

        System.out.println(LibraryPatronSystem.Text.BLUE + "\nREMOVING AN EXISTING PATRON!");
        System.out.println("----------------------------\n" + LibraryPatronSystem.Text.RESET);

        while (userTryAgain.equals("y")){
            System.out.print("Enter the Patron ID you would like to remove: ");
            int userStatedPatronID = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            for (Patrons patron : patronStorage) {
                if (patron.getPatronID() == userStatedPatronID) {
                    patronStorage.remove(patron);
                    System.out.println(LibraryPatronSystem.Text.MAGENTA + "Patron ID, " +
                            patron.getPatronID() + ", has been removed!" + LibraryPatronSystem.Text.RESET);
                    patronFound = true;
                    break;
                }
            }

            userTryAgain = "n";

            if (!patronFound) {
                System.out.println(LibraryPatronSystem.Text.BRIGHT_RED + "Patron ID was not found." + LibraryPatronSystem.Text.RESET);
                System.out.print("Would you like to try again? (y or n): ");
                userTryAgain = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));
            }
        }
    }

    /* Method Name: Find Patron By ID Method
     * Purpose: Allows the user to search for a patron using the Patron ID
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void findPatronByID(List<Patrons> patronStorage) {
        boolean patronFound = false;
        String userTryAgain = "y";

        System.out.println(LibraryPatronSystem.Text.BLUE + "\nSEARCHING FOR AN EXISTING PATRON!");
        System.out.println("---------------------------------\n" + LibraryPatronSystem.Text.RESET);

        while (userTryAgain.equals("y")){
            System.out.print("Enter the Patron ID you would like to search for: ");
            int userStatedPatronID = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            for (Patrons patron : patronStorage) {
                if (patron.getPatronID() == userStatedPatronID) {
                    System.out.println("Patron Found!");
                    System.out.println(patron);
                    patronFound = true;
                    break;
                }
            }

            userTryAgain = "n";

            if (!patronFound) {
                System.out.println(LibraryPatronSystem.Text.BRIGHT_RED + "Patron ID was not found." + LibraryPatronSystem.Text.RESET);
                System.out.print("Would you like to try again? (y or n): ");
                userTryAgain = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));
            }
        }
    }

    /* Method Name: Get All Patrons Method
     * Purpose: Prints all Patrons in given list to the CMI
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void getAllPatrons(List<Patrons> patronStorage) {
        System.out.println(LibraryPatronSystem.Text.BLUE + "\nPRINTING ALL PATRONS!");
        System.out.println("---------------------\n" + LibraryPatronSystem.Text.RESET);

        for (Patrons patron : patronStorage) {
            System.out.println(patron);
        }
    }

    /* Method Name: Increase Fine Amount Method
     * Purpose: Allows the user to increase a specified Patron (Using Patron ID) and adds the new
     *          amount, the user specifies, to the patrons current amount.
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void increaseFineAmount(List<Patrons> patronStorage) {
        boolean patronFound = false;
        String userTryAgain = "y";

        System.out.println("\nCHANGING FINE AMOUNT FOR EXISTING PATRON!");
        System.out.println("-----------------------------------------\n");

        // Lets the user continue attempting to find the patron until one is found.
        while (userTryAgain.equals("y")){
            System.out.print("Enter the Patron ID you would like to search for: ");
            int userStatedPatronID = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            for (Patrons patron : patronStorage) {
                if (patron.getPatronID() == userStatedPatronID) {
                    System.out.println("Patron Found!");
                    System.out.println("Current Fine Amount: " + patron.getOverdueFine());
                    System.out.print("How much do you want to increase the fine amount? (Example 12.34): ");
                    double newFineAmount = scnr.nextDouble();
                    scnr.nextLine(); // Used to consume the newline

                    try {
                        patron.setOverdueFine(patron.getOverdueFine() + newFineAmount);
                    } catch (FineLimitExceededException e) {
                        System.err.println(LibraryPatronSystem.Text.BRIGHT_RED + "Cannot add fine: " + e.getMessage()
                                + LibraryPatronSystem.Text.RESET);
                    }

                    System.out.println(LibraryPatronSystem.Text.MAGENTA + "New Fine Amount: " + patron.getOverdueFine()
                            + LibraryPatronSystem.Text.RESET);
                    patronFound = true;
                    break;
                }
            }

            userTryAgain = "n";

            if (!patronFound) {
                System.out.println(LibraryPatronSystem.Text.BRIGHT_RED + "Patron ID was not found." + LibraryPatronSystem.Text.RESET);
                System.out.print("Would you like to try again? (y or n): ");
                userTryAgain = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));
            }
        }
    }

    /* Method Name: Decrease Fine Amount Method
     * Purpose: Allows the user to decrease a specified Patron (Using Patron ID) and adds the new
     *          amount, the user specifies, to the patrons current amount.
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void decreaseFineAmount(List<Patrons> patronStorage) {
        boolean patronFound = false;
        String userTryAgain = "y";

        System.out.println(LibraryPatronSystem.Text.BLUE + "\nCHANGING FINE AMOUNT FOR EXISTING PATRON!");
        System.out.println("-----------------------------------------\n" + LibraryPatronSystem.Text.RESET);

        while (userTryAgain.equals("y")){
            System.out.print("Enter the Patron ID you would like to search for: ");
            int userStatedPatronID = scnr.nextInt();
            scnr.nextLine(); // Used to consume the newline

            for (Patrons patron : patronStorage) {
                if (patron.getPatronID() == userStatedPatronID) {
                    System.out.println("Patron Found!");
                    System.out.println("Current Fine Amount: " + patron.getOverdueFine());
                    System.out.print("How much do you want to decrease the fine amount? (Example 12.34): ");
                    double newFineAmount = scnr.nextDouble();
                    scnr.nextLine(); // Used to consume the newline

                    try {
                        patron.setOverdueFine(patron.getOverdueFine() - newFineAmount);
                    } catch (FineLimitExceededException e) {
                        System.err.println(LibraryPatronSystem.Text.BRIGHT_RED + "Cannot add fine: " + e.getMessage()
                                + LibraryPatronSystem.Text.RESET);
                    }

                    System.out.println("New Fine Amount: " + patron.getOverdueFine());
                    patronFound = true;
                    break;
                }
            }

            userTryAgain = "n";

            if (!patronFound) {
                System.out.println(LibraryPatronSystem.Text.BRIGHT_RED + "Patron ID was not found." + LibraryPatronSystem.Text.RESET);
                System.out.print("Would you like to try again? (y or n): ");
                userTryAgain = String.valueOf(scnr.nextLine().toLowerCase().charAt(0));
            }
        }
    }

    /* Method Name: Load Patrons From File Method
     * Purpose: Allows the user to specify a file location and add all patrons in that
     *          file to the system.
     * Parameters: The List that holds all Patrons
     * Return Value: Void/None
     */
    public static void loadPatronsFromFile(List<Patrons> patronStorage) {

        System.out.println(LibraryPatronSystem.Text.BLUE + "\nLOADING PATRONS FROM FILE!");
        System.out.println("--------------------------\n" + LibraryPatronSystem.Text.RESET);

        System.out.print("Please enter the file location: ");
        String fileLocation = scnr.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileLocation))){
            String line;

            while ((line = reader.readLine()) != null) {
                // Splits lines based on dashes per requirement.
                String[] variables = line.split("-");
                if (variables.length == 6) {
                    Patrons newPatron = getPatrons(variables);

                    patronStorage.add(newPatron);
                }
            }

            System.out.println(LibraryPatronSystem.Text.MAGENTA + "\nPatrons added successfully!"
                    + LibraryPatronSystem.Text.RESET);

        } catch (IOException e) {
            System.err.println(LibraryPatronSystem.Text.BRIGHT_RED + "Error reading from file: " + e.getMessage()
                    + LibraryPatronSystem.Text.RESET);
        }

    }

    /* Method Name: Get Patrons Method
     * Purpose: Called by loadPatronsFromFile method. Used to take a String list and
     *          split it into the specified variables and create a new patron with it.
     * Parameters: A List of Strings from each line of the text document
     * Return Value: Void/None
     */
    private static Patrons getPatrons(String[] variables) {
        String patronFName = variables[0];
        String patronLName = variables[1];
        String patronStreetAddress = variables[2];
        String patronCity = variables[3];
        String patronState = variables[4];
        String patronZipCode = variables[5];

        return new Patrons(patronFName, patronLName, patronStreetAddress,
                patronCity, patronState, patronZipCode);
    }

    /* Method Name: To String Method Override
     * Purpose: Overrides the original toString method to change how it prints when someone
     *          prints a Patron.
     * Parameters: None
     * Return Value: String with all Patron information.
     */
    @Override
    public String toString() {
        return  "\nPatron ID:               " + this.patronID + "\n" +
                "Patron Full Name:        " + this.patronFName + " " + this.patronLName + "\n" +
                "Patron Address:          " + this.patronStreetAddress + "\n                         " +
                this.patronCity + ", " + this.patronState + " " + this.patronZipCode + "\n" +
                "Current Overdue Fine:    " + this.patronOverdueFine;
    }
}

