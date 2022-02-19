import java.util.ArrayList;  // Import package for ArrayList
import java.util.Scanner;  // Import package for user input

// Declare the class and implement the packages
public class EnhancementOne {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> petList;

    // This is the main method that Java looks for when ran
    public static void main(final String[] args) {

        showMainMenu(); // Method called to display the main menu at the start of the program
        scan.close();  // Method called to close the scanner once it has been opened
    }

    // Options for the main menu
    public static void showMainMenu() {
        System.out.println("--- MAIN MENU ---");
        System.out.println("1. Create Pet");
        System.out.println("2. Update Pet");
        System.out.println("3. Delete Pet");
        System.out.println("4. Show Pets");
        System.out.println("5. Exit");

        System.out.print("Enter your Choice : ");

        // Allows for user input once the menu has been displayed
        int option = scan.nextInt();

        // The switch block that determines what happens once the user has made a selection
        switch (option) {
            case 1:
                createPet(); // Calls the create pet method
                break;
            case 2:
                updatePet();  // Calls the update pet method
                break;
            case 3:
                deletePet();  // Calls the delete pet method
                break;
            case 4:
                showPets();  // Calls the show pets method
            case 5:
                System.exit(0); // Allows the user to exit the menu
                break;
            
            // If the user enters an invalid selection this will be printed out
            default:
                System.out.println("Invalid option!");
                showMainMenu();  // Returns to the main menu
        }
}

    // Method used to create a new pet
    public static void createPet() {
        Scanner myObj = new Scanner(System.in);  // Allows user input

        System.out.print("Enter Pet Name: ");
        String newPet = myObj.nextLine();

        // Creates a new Array for the pet name to be stored
        String newPetArray[] = newPet.split(" ");
        petList = new ArrayList<>();

        // Every time a new pet is created it moves up one node in the list
        for (int i = 0; i < newPetArray.length; i++) {
            petList.add(newPetArray[i]);
        }
        
        System.out.println("Pets in list are " + petList);  // Prints the items that are stored in "petList"
        showMainMenu();
    }

    // Method used to update an exsisting pet
    public static void updatePet() {

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
        showMainMenu();
    }


    // Method used to detlete a pet
    public static void deletePet() {
        System.out.println("Enter the name of the pet to be deleted");
        String name = scan.next();
        for (int i = 0; i < petList.size(); i++) {
            // Gets the name the user has entered
            if (petList.get(i).equals(name)) {
                // Removes the name from the list
                petList.remove(i);
                break;
            }
        }
        System.out.println("pets in list after deleting the specific pet " + petList);  // Prints the items that are stored in "petList"
        showMainMenu();
    }

    // Method used to show pets in petList
    public static void showPets() {
        System.out.println(petList);
        showMainMenu();
    }
}