# Data Structures and Algorithms

## Narative

###  Briefly describe the artifact. What is it? When was it created?

This artifact will be building off my first enhancement of the menu driven pet application. This will enhance the features by adding an ArrayList to the menu options for easy modifications. It will contain a sorting algorithm that will place the pets in alphabetical order and it will have the option to print out a ticket for the user with prices based on the pets weight. It was originally started during my first year at SNHU.

###  Justify the inclusion of the artifact in your ePortfolio. Why did you select this item? What specific components of the artifact showcase your skills and abilities in algorithms and data structure? How was the artifact improved?

I included this artifact in my ePortfolio because it demonstrates my ability to use different data structures such as using a while(true) loop to stay in the main menu in order to keep the items in the ArrayList from being removed. It also shows that I understand different algorithms by importing and using the sort() method to arrange the items in the ArrayList. The artifact has improved by being able to function like something a pet grooming operation would use. 

```
import java.util.ArrayList;  // Import package for ArrayLists
import java.util.Scanner;  // Import package for user input
import java.util.Collections; // Import package to create collection in order to sort

// Declare the class and implement the packages
public class EnhancementTwo {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> petList;
    static ArrayList<Integer> petWeight;

    // This is the main method that Java looks for when ran
    public static void main(final String[] args) {
        
        // Menu data in array format
        int options[] = {1,2,3,4,5,6}; // Option numbers for menu Array
        String menuItems[] = {"Create Pets", "Update Pets", "Delete Pets", "Show Pets", "Print Ticket", "Exit"};  // Options for menu Array

        showMainMenu(options, menuItems);  // Method called to display the main menu using the Array at the start of the program
        scan.close();  // Method called to close the scanner once it has been opened
    }

    // Options for the main menu
    public static void showMainMenu(int opts [], String items[]) {
        while (true)
        {
        int options[] = {1,2,3,4,5,6};  // Option numbers for menu Array
        String menuItems[] = {"Create Pets", "Update Pets", "Delete Pets", "Show Pets", "Print Ticket", "Exit"};  // Options for menu Array
        System.out.println("--- MAIN MENU ---");
        // Dispalys what is in the Array to the showMainMenu method
        for (int i = 0; i < opts.length; i++){
            System.out.printf("%d. %s\n", opts[i], items[i]);
        }
        System.out.println("=================");
        System.out.print("Enter your Choice : ");

        // Allows for user input once the menu has been displayed
        int option = scan.nextInt();

        // The switch block that determines what happens once the user has made a selection
        switch (option) {
            case 1:
                createPets();  // Calls the create pet method
                break;
            case 2:
                updatePets();  // Calls the update pet method
                break;
            case 3:
                deletePets();  // Calls the delete pet method
                break;
            case 4:
                showPets();  // Calls the show pet method
                break;
            case 5:
                printTicket();  // Calls the print ticket method
                break;
            case 6:
            System.exit(0);  // Allows the user to exit the menu
            break;
            // If the user enters an invalid selection this will be printed out
            default:
                System.out.println("Invalid option!");
                showMainMenu(options, menuItems);  // Returns to the menu
        }
    }
}

    // Method used to create a new pet
    public static void createPets() {

        Scanner myObj = new Scanner(System.in);  // Allows for user input

        System.out.print("Enter Pet Names For List: ");
        String newPet = myObj.nextLine();

        // Creates a new Array for the pet name to be stored
        String newPetArray[] = newPet.split(" ");
        petList = new ArrayList<>();
        
        // Every time a new pet is created it moves up one node in the list
        for (int i = 0; i < newPetArray.length; i++) {
            petList.add(newPetArray[i]);
        }
  
        System.out.println("Pets in list are " + petList);  // Prints the items that are stored in "petList"
    }

    // Method used to update an exsisting pet
    public static void updatePets() {

        System.out.println("Enter the name of the pet to be updated");
        String name = scan.next();
        System.out.println("Enter the updated name");
        String newName = scan.next();

        // Iterates through the ArrayList until the name is found
        for (int i = 0; i < petList.size(); i++) {
            // Get the name to be updated
            if (petList.get(i).equals(name)) {
                // Set the new name to the existing name
                petList.set(i, newName);
                break;
            }
        }
        System.out.println("Pets in list after updating the pet  " + petList);  // Prints the items that are stored in "petList"
    }

    // Method used to detlete a pet
    public static void deletePets() {

        System.out.println("Enter the name of the pet to be deleted");
        String name = scan.next();
        for (int i = 0; i < petList.size(); i++) {
            // Gets the name the user has entered
            if (petList.get(i).equals(name)) {
                // Removes the name from the list
                petList.remove(i);
        System.out.println("Your pet has been removed.");
                break;
            }
        }
        System.out.println("Pets in list after deleting the specific pet " + petList);  // Prints the items that are stored in "petList"
    }

    // Method used to show pets in petList
    public static void showPets() {
        Collections.sort(petList);  // Uses sort to place the items in the ArrayList in alphabetical order
        System.out.println("These are the pets that you currently have in alphabetical order: " + petList);  // Prints petList in alphabetical order
        }

    // Method used to print out a ticket for the user
    public static void printTicket() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter the weight of your pet: ");
        int petWeight = scan.nextInt();  // Allows the user to input the weight of the pet
        // If else statement to determine if the pets weight is under 30 pounds
        if(petWeight < 30){
            System.out.println("Grooming will be $50.00");  // Price will be $50.00 if the pet is 29 pounds or under
        }
        else {
            System.out.println("Gromming will be $75.00");  // Price will be $75.00 if the pet is 30 pounds or over
      }
    }
}
```
