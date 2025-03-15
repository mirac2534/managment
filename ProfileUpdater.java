import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProfileUpdater {

    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt";
    private static final String DEPARTMENT_FOLDER = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\departments";

    public static void updateWorkDays() {
        List<String> updatedProfiles = new ArrayList<>();
        Map<String, List<String>> departmentUpdates = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                if (details.length >= 9) {
                    LocalDate entryDate = LocalDate.parse(details[7], formatter);
                    LocalDate currentDate = LocalDate.now();
                    Period period = Period.between(entryDate, currentDate);

                    // Update the working time
                    String updatedSubDate = "Year:" + period.getYears() + ",Month:" + period.getMonths() + ",Day:" + period.getDays();
                    details[8] = updatedSubDate;

                    // Save updated profile
                    updatedProfiles.add(String.join(",", details));

                    // Keep updates by department
                    String department = details[3]; // Department information
                    departmentUpdates.computeIfAbsent(department, k -> new ArrayList<>()).add(String.join(",", details));
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
            return;
        }

        // The main profile.update txt file
        writeToFile(PROFILE_FILE, updatedProfiles);

        // Update department-based files
        for (Map.Entry<String, List<String>> entry : departmentUpdates.entrySet()) {
            String departmentFile = DEPARTMENT_FOLDER + "\\" + entry.getKey() + ".txt";
            writeToFile(departmentFile, entry.getValue());
        }

        System.out.println("All profiles and department files updated successfully.");
    }

    // Writes the given list to the specified file
    private static void writeToFile(String fileName, List<String> content) {
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println(fileName + " updated.");
        } catch (IOException e) {
            System.out.println("File writing error (" + fileName + "): " + e.getMessage());
        }
    }
}
