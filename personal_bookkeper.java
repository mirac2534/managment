import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class personal_bookkeper extends Profile.Profilee{

    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt";
    private static final String DEPARTMENT_FOLDER = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments";

    public personal_bookkeper(String name, String lastName, String TC, String department, String password, String ID, String salary, String date, String subDate) {
        super(name, lastName, TC, department, password, ID, salary, date, subDate);
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n=== Personnel Management System ===");
            System.out.println("1 - Update Salary");
            System.out.println("2 - Exit");
            System.out.print("Make your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear Enter character

            switch (choice) {
                case 1:
                    System.out.println("Enter Personnel ID:");
                    String userID = scanner.nextLine();
                    changeSalary(userID, scanner);
                    break;
                case 2:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void changeSalary(String userID, Scanner scanner) {

        File profile = new File(PROFILE_FILE);

        if (!profile.exists()) {
            System.out.println("Error: File not found!");
            return;
        }

        String departmentFile = null;
        double oldSalary = 0;
        String department = null;
        int workYears = 0;
        Map<String, String[]> profileMap = new LinkedHashMap<>();

        // Read profile.txt file and find the corresponding personnel
        try (BufferedReader reader = new BufferedReader(new FileReader(profile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 9) {
                    profileMap.put(details[4], details); // ID -> Profile details

                    if (details[4].equals(userID)) {
                        department = details[3]; // Department name
                        oldSalary = Double.parseDouble(details[6]); // Salary
                        workYears = Integer.parseInt(details[8]); // Work years
                        departmentFile = DEPARTMENT_FOLDER + "\\" + department + ".txt"; // Corresponding department file
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
            return;
        }

        if (departmentFile == null) {
            System.out.println("Error: User ID not found!");
            return;
        }

        System.out.println("Department: " + department + " | Current Salary: " + oldSalary + " | Work Experience: " + workYears + " years");
        System.out.print("New Salary: ");
        double newSalary = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer

        // **1. Update profile.txt**
        if (profileMap.containsKey(userID)) {
            profileMap.get(userID)[6] = String.valueOf(newSalary); // Assign new salary
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE))) {
            for (String[] details : profileMap.values()) {
                writer.write(String.join(",", details));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File writing error: " + e.getMessage());
            return;
        }

        // **2. Update Department File**
        File deptFile = new File(departmentFile);
        if (!deptFile.exists()) {
            System.out.println("Error: " + departmentFile + " not found!");
            return;
        }

        Map<String, String[]> departmentMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(deptFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 9) {
                    departmentMap.put(details[4], details);
                }
            }
        } catch (IOException e) {
            System.out.println("Department file reading error: " + e.getMessage());
            return;
        }

        if (departmentMap.containsKey(userID)) {
            departmentMap.get(userID)[6] = String.valueOf(newSalary); // Assign new salary
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(departmentFile))) {
            for (String[] details : departmentMap.values()) {
                writer.write(String.join(",", details));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Department file writing error: " + e.getMessage());
            return;
        }

        System.out.println("Salary successfully updated! (profile.txt and " + departmentFile + ")");
    }

}
