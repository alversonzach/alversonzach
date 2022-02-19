# Software Design and Engineering

## Narrative

## Briefly describe the artifact. What is it? When was it created?

The artifact that I have chosen to enhance is a program for a pet boarding and grooming company. It is a menu driven program that allows for user input to go through the menu and access all the options. I attempted this project in my first year at SNHU.

## Justify the inclusion of the artifact in your ePortfolio. Why did you select this item? What specific components of the artifact showcase your skills and abilities in software development? How was the artifact improved?

I selected this artifact because it contains areas that I am familiar with. In order to achieve the enhancements, I am having to go outside of my comfort zone and learn some new things. Creating the showMainMenu() method with a switch operation show that I can iterate through different options and allow a user to input their desired selections. The artifact now gives the user the opportunity to decide where to go in the program.

## Artifact One

```
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
        System.out.println("1. Create Pet");![Screenshot (15)](https://user-images.githubusercontent.com/97413992/154815142-8588eb2b-8d74-4c26-aaff-6bf60279ef4d.png)

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
```

### Outcome

![Screenshot (15)](https://user-images.githubusercontent.com/97413992/154815161-f3619d8a-55f1-4f5a-b5d9-5d1dc563a8d4.png)

![Screenshot (16)](https://user-images.githubusercontent.com/97413992/154815224-2770a9c7-a03f-4cd1-9e07-8da6eac5250a.png)

## Reflect on the process of enhancing and/or modifying the artifact. What did you learn as you were creating it and improving it? What challenges did you face?

I now have a deeper understanding of ArrayLists and how to use them with a scanner. Once the user has entered the name it is then stored in the ArrayList starting at index 0. ArrayLists can be used to store data and change the data too. I was originally going to use an Array function but I learned that you have to declare the limit. Using the ArrayList function allowed me to create the list without having to set the limit. 
