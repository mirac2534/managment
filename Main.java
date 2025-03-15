import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean profileExists = Profile.profileExists();  // Checks if there is any profile in the file

        if (!profileExists) {
            System.out.println("No profiles found. Please create a new profile.");
            personal_IK personalIK = new personal_IK("", "", "", "", "", "", "", "","");
            personalIK.createProfile(scanner);
        }

        // User login screen
        System.out.println("Welcome");
        System.out.print("Enter your ID: ");
        String inputID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        Profile.Profilee currentProfile = Profile.getProfileFromFile(inputID, inputPassword);
        if(currentProfile != null){
            System.out.println("Login successful!");
            System.out.println("Welcome " + currentProfile.getName());

            // Get user's department
            String department = currentProfile.getDepartment();

            switch (department){
                case "IK":
                    new personal_IK(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "bookkeper":
                    new personal_bookkeper(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "manager":
                    new personal_manager(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "packer":
                    new personal_packer(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "ironer":
                    new personal_ironer(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "machinist":
                    new personal_machinist(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                case "debugging":
                    new personal_debugging(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                    case "IT":
                    new personal_IT(
                            currentProfile.getName(), currentProfile.getLastName(), currentProfile.getTC(),
                            currentProfile.getDepartment(), currentProfile.getPassword(), currentProfile.getID(),
                            currentProfile.getSalary(), currentProfile.getDate(), currentProfile.getSubDate()
                    ).startMenu();
                    break;
                default:
                    System.out.println("Error: Unknown department '" + currentProfile.getDepartment() + "'!");
                    break;
            }

        }else {
            System.out.println("Invalid ID or password!");
        }

        scanner.close();
    }
}
