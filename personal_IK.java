import java.awt.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class personal_IK extends Profile.Profilee {

    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt";
    private static final String DEPARTMENT_FOLDER = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments";

    public personal_IK(String name, String lastName, String TC, String department, String password, String ID, String salary, String date, String subDate) {
        super(name, lastName, TC, department, password, ID, salary, date, subDate);
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Personnel Management System ===");
            System.out.println("1 - Add New Personnel");
            System.out.println("2 - View Profiles");
            System.out.println("3 - Remove Personnel");
            System.out.println("4 - Exit");
            System.out.print("Make your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear Enter character

            switch (choice) {
                case 1:
                    createProfile(scanner);
                    break;
                case 2:
                    showProfile(scanner);
                    break;
                case 3:
                    deletePersonal();
                case 4:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addProfile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE, true))) {
            System.out.print("Enter Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String userSurname = scanner.nextLine();
            System.out.println("Enter TC: ");
            String userTC = scanner.nextLine();
            System.out.println("Enter Department: ");
            String userDepartment = scanner.nextLine();
            String userID;
            do {
                System.out.print("Enter ID: ");
                userID = scanner.nextLine();
                if (Profile.IDExists(userID)) {
                    System.out.println("ID already in use, please choose a different ID.");
                }
            } while (Profile.IDExists(userID));

            System.out.println("Enter Password: ");
            String userPassword = scanner.nextLine();
            System.out.println("Enter Salary: ");
            String userSalary = scanner.nextLine();
            System.out.println("Enter Entry Date (dd-MM-yyyy): ");
            String userDate = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate entryDate = LocalDate.parse(userDate, formatter);
            LocalDate currentDay = LocalDate.now();
            Period subtraction = Period.between(entryDate, currentDay);

            String subDate = "Year:" + subtraction.getYears() + ",Month:" + subtraction.getMonths() + ",Day:" + subtraction.getDays();

            saveProfile(userName, userSurname, userTC, userDepartment, userID, userPassword, userSalary, userDate, subDate);

            System.out.println("Profile added successfully.");
        } catch (IOException e) {
            System.out.println("Could not create a profile.");
        }
    }

    public void createProfile(Scanner scanner) {
        addProfile(scanner);
    }

    private static void saveProfile(String userName, String userSurname, String userTC, String userDepartment, String userID, String userPassword, String userSalary, String userDate, String subDate) {
        String userData = userName + "," + userSurname + "," + userTC + "," + userDepartment + "," + userID + "," + userPassword + "," + userSalary + "," + userDate + "," + subDate;

        String[] files = {PROFILE_FILE, DEPARTMENT_FOLDER + "\\" + userDepartment + ".txt"};

        for (String fileName : files) {
            try {
                File file = new File(fileName);
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(userData);
                writer.newLine();
                writer.close();

                System.out.println(fileName + " file updated.");
            } catch (IOException e) {
                System.out.println("File writing error (" + fileName + "): " + e.getMessage());
            }
        }
    }

    private void showProfile(Scanner scanner) {
        System.out.println("\n=== View Profiles ===");
        System.out.println("1 - Show All Profiles");
        System.out.println("2 - Show a Specific Department");
        System.out.print("Make your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

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
            default:
                System.out.println("Invalid choice.");
        }
    }

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

    public void deletePersonal() {
        deleteProfile();
    }

    private void deleteProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the department of the personnel to be deleted: ");
        String userDepartment = scanner.nextLine().trim();

        System.out.print("Enter the ID of the personnel to be deleted: ");
        String userID = scanner.nextLine().trim();

        String departmentFile = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments\\department/" + userDepartment + ".txt";

        File file = new File(departmentFile);
        if (!file.exists()) {
            System.out.println("Error: " + departmentFile + " not found or incorrect department name!");
            return;
        }

        Map<String, String[]> profileMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] profileDetails = line.split(",");
                if (profileDetails.length >= 9) {
                    profileMap.put(profileDetails[5], profileDetails);
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
            return;
        }

        if (profileMap.containsKey(userID)) {
            profileMap.remove(userID);
        } else {
            System.out.println("The specified ID was not found.");
            return;
        }

        StringBuilder updatedContent = new StringBuilder();
        for (String[] profileDetails : profileMap.values()) {
            updatedContent.append(String.join(",", profileDetails)).append("\n");
        }

        String[] files = {
                "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt",
                departmentFile
        };

        for (String fileName : files) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
                writer.write(updatedContent.toString());
                System.out.println(fileName + " file updated.");
            } catch (IOException e) {
                System.out.println("File writing error (" + fileName + "): " + e.getMessage());
            }
        }
    }
}
