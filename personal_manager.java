import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class personal_manager extends Profile.Profilee{

    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt";
    private static final String DEPARTMENT_FOLDER = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments";

    public personal_manager(String name, String lastName, String TC, String department, String password, String ID, String salary, String date, String subDate){
        super(name, lastName, TC, department, password, ID, salary, date, subDate);
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n=== Personnel Management System ===");
            System.out.println("1 - View Profiles");
            System.out.println("2 - Exit");
            System.out.print("Make your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear Enter character

            switch (choice) {
                case 1:
                    showProfile(scanner);
                    break;
                case 2:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void showProfile(Scanner scanner) {
        System.out.println("\n=== View Profiles ===");
        System.out.println("1 - Show All Profiles (Profile.txt)");
        System.out.println("2 - Show Profiles of a Specific Department");
        System.out.println("3 - Exit");
        System.out.print("Make your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear Enter character

        switch (choice) {
            case 1:
                displayFile(PROFILE_FILE);
                break;
            case 2:
                System.out.print("Enter the department name you want to view: ");
                String department = scanner.nextLine();
                String departmentFile = DEPARTMENT_FOLDER + "/" + department + ".txt";
                displayFile(departmentFile);
                break;
            case 3:
                System.out.println("Exiting the program...");
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Reads and displays the file content in the terminal
    private static void displayFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Error: " + fileName + " not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("\n--- " + fileName + " ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }
    }

}
