import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Profile {

    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\çalışmalarım\\management\\managment\\profile.txt";

    static class Profilee {
        private String name;
        private String lastName;
        private String TC;
        private String department;
        private String password;
        private String ID;
        private String salary;
        private String date;
        private String subDate;

        public Profilee(String name, String lastName, String TC, String department, String ID, String password, String salary, String date, String subDate) {
            this.name = name;
            this.lastName = lastName;
            this.TC = TC;
            this.department = department;
            this.ID = ID;
            this.password = password;
            this.salary = salary;
            this.date = date;
            this.subDate = subDate;
        }

        // Getter metodları
        public String getName() { return name; }
        public String getLastName() { return lastName; }
        public String getTC() { return TC; }
        public String getDepartment() { return department; }
        public String getPassword() { return password; }
        public String getID() { return ID; }
        public String getSalary() { return salary; }
        public String getDate() { return date; }
        public String getSubDate(){return subDate;}
    }

    // Checks if there is a profile in the file. If the file exists and contains data, it returns true
    public static boolean profileExists() {
        File file = new File(PROFILE_FILE);
        return file.exists() && file.length() > 0;
    }

    // Verifies the ID and password in the file and returns the profile if they match
    public static Profilee getProfileFromFile(String ID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String profileID = details[4];
                String profilePassword = details[5];

                if (profileID.equals(ID) && profilePassword.equals(password)) {
                    return new Profilee(details[0], details[1], details[2], details[3], details[4], details[5], details[6], details[7], details[8]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Checks if the entered ID is already in use
    public static boolean IDExists(String ID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[4].equals(ID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
