# This is the original program that I started with

```
import java.util.Scanner;  // Import package for user input

// Declare the class and implement the packages
public class Original {

    // This is the main method that Java looks for when ran
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String petName;
        String petType;
        int petAge;
        String petOwner;

        while(true) {
            System.out.println("Enter Pet Name: ");
            petName = scnr.nextLine();
            System.out.println("Pet's name is :" + petName);

            System.out.println("Enter Pet Type: ");
            petType = scnr.nextLine();
            System.out.println("Pet's Type Is :" + petType);

            System.out.println("Enter Pet's Age : ");
            petAge = scnr.nextInt();
            System.out.println("Pet's Age Is: " + petAge);

            System.out.println("Enter Pet's Owner: ");
            petOwner = scnr.nextLine();
            System.out.println("Pet's Owner is: " + petOwner);
        }       
    }
}   
```
