import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class personal_ironer extends Profile.Profilee {

    private static final String DEPARTMENT_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments\\ironer.txt";
    private String userID; // Store user ID

    public personal_ironer(String name, String lastName, String TC, String department, String password, String ID, String salary, String date, String subDate) {
        super(name, lastName, TC, department, password, ID, salary, date, subDate);
        this.userID = ID; // Store ID to use without a getter method
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Personnel Management System ===");
            System.out.println("1 - Show My Details");
            System.out.println("2 - Show Profiles in My Department");
            System.out.println("3 - Exit");
            System.out.print("Make your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear Enter character

            switch (choice) {
                case 1:
                    showProfileFromFile();
                    break;
                case 2:
                    displayDepartmentProfiles();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Show the user's own profile by reading from the file
    private void showProfileFromFile() {
        File file = new File(DEPARTMENT_FILE);
        if (!file.exists()) {
            System.out.println("Error: " + DEPARTMENT_FILE + " not found!");
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] profileDetails = line.split(",");
                if (profileDetails.length >= 9 && profileDetails[5].equals(userID)) { // Find by user's ID
                    System.out.println("\n=== My Details ===");
                    System.out.println("Name: " + profileDetails[0]);
                    System.out.println("Last Name: " + profileDetails[1]);
                    System.out.println("TC: " + profileDetails[2]);
                    System.out.println("Department: " + profileDetails[3]);
                    System.out.println("ID: " + profileDetails[5]);
                    System.out.println("Salary: " + profileDetails[6]);
                    System.out.println("Start Date: " + profileDetails[7]);
                    System.out.println("Work Duration: " + profileDetails[8]);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Error: User details not found.");
        }
    }

    // List profiles in the department by showing specific details
    private void displayDepartmentProfiles() {
        File file = new File(DEPARTMENT_FILE);
        if (!file.exists()) {
            System.out.println("Error: " + DEPARTMENT_FILE + " not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("\n=== Employees in the Department ===");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] profileDetails = line.split(",");
                if (profileDetails.length >= 9) {
                    // Show only name, last name, start date, and work duration
                    System.out.println("Name: " + profileDetails[0] +
                            ", Last Name: " + profileDetails[1] +
                            ", Start Date: " + profileDetails[7] +
                            ", Work Duration: " + profileDetails[8]);
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }
    }
}
