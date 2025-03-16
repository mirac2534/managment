


# Managment 

  
### Project Introduction: Managment for Textile  
  
This project is a **personnel tracking system** that can be used by a textile company written in  **java**.   

---  
 
 ### Application Workflow:  
## 1-**Main class:**  
- In the Main class, he first checks to see if there are any staff members. If there are no staff, it creates an object from an empty HR department and adds staff. It should be remembered that adding personnel is only accessible to the HR department. If there is a staff member, he asks for his ID and password to log in. If the ID and password are correct, it retrieves the department using the get method and runs the menu belonging to that department. If the ID or password entered is incorrect, it will give a warning and log out of the program.  
 
## 2-**Profile class**
### 2-1- Profile Inner Class

Represents an individual investor's profile.  
Fields:  
- name (String): personal's name  
- lastName (String): personal's surname  
- TC (String): personal's TC number  
- department (String): department of the personal
- password (String): personal's password 
- ID (String): personal's ID
- salary (String): personal's salary
- date(String): personal's entered day
- subDate(String): how long the personal has been working 
The constructor initializes the profile instance with the specified fields.

### 2-2- profileExists Method
    public static boolean profileExists()  
This method checks if the profile file ('profile.txt') exists and if it contains data. Returns true if a profile exists; otherwise, returns false.
 
### 2-3- getProfileFromFile Method
    public static Profilee getProfileFromFile(String ID, String password)
   This method pulls all the information of that object if the entered ID and password match the ID and password of the registered person in the file.

### 2-4- IDExists Method
    public static boolean IDExists(String ID)
This method checks whether there is such an ID when a new contact is created, because each ID is unique to the person.

## 3-**personal_IK class**
### 3-1-  startMenu
    public void startMenu()
It is the menu where adding personnel, displaying personnel, deleting personnel and exiting operations will be started.
### 3-2-  addProfile Method
    private void addProfile(Scanner scanner)
This method is for adding a new staff. After receiving the first name, last name, TC, department, ID information, it checks to see if there are any personnel who have used this ID in advance.
--If there are no personnel using this ID, it asks for password, salary, login date information. It also automatically calculates the working time according to the login date. Then he registers the new staff in the files of general employees and the department to which he belongs.
--If there are personnel using this ID, it returns an error message and returns it to the menu.
 
### 3-3-  saveProfile Method
    private static void saveProfile(String userName, String userSurname, String userTC, String userDepartment, String userID, String userPassword, String userSalary, String userDate, String subDate)
  Registers the newly added personnel in the general personnel file and the department file to which it belongs.
### 3-4-  showProfile Method
    private void showProfile(Scanner scanner)
   This method tells you whether to look at general staff or people in a specific department.
### 3-5-  displayFile Method
	private static void displayFile(String fileName)
This method prints the contacts belonging to that file on the screen with the file name from the showProfile method.
### 3-6-  deleteProfile Method
    private void deleteProfile()
This method deletes the personnel with the department name and ID number that you entered. It will be deleted from the department and general personnel files to which it belongs.
## 4-**personal_bookkeper class**
### 4-1-  startMenu
    public void startMenu()
It is the menu where updating salary of personnel and exiting operations will be started.
### 4-1-  changeSalary Method
    private void changeSalary(String userID, Scanner scanner)
This method updates the salary information of the ID number you entered. It then updates his new salary from the general staff and salary information from the department files to which he belongs.
## 5-**personal_manager class**
### 5-1-  startMenu
    public void startMenu()
It is the menu where displaying personnel and exiting operations will be started.
### 5-2-  showProfile Method
    private void showProfile(Scanner scanner)
 This method tells you whether to look at general staff or people in a specific department.
### 5-3-  displayFile Method
	private static void displayFile(String fileName)
This method prints the contacts belonging to that file on the screen with the file name from the showProfile method.

## 6-**personal_another class**
Do not look at the name personal_another, because the contents of the classes belonging to IT, machinist, debugger, packer and ironer are the same. That's why I'm going to make a statement through someone.
### 6-1-  startMenu
    public void startMenu()
It is the menu where only their information will be displayed, the people in their department will be displayed, and exit operations will be started.
### 6-2-  showProfileFromFile Method
    private void showProfileFromFile()
This method shows the person's own personal information.
### 6-3-  displayDepartmentProfiles Method
	private void displayDepartmentProfiles()
This method shows the name, surname, employment date and number of years of employment of all personnel in the department to which the person belongs.
## 7-**ProfileUpdater class**
This class is for automatically updating the working time from the files of the general staff and the department to which it belongs. It is defined in the Jar file and automatically updates the working time at 21.00 every day thanks to the Task Scheduler. 
### 7-1-  updateWorkDays Method
    public static void updateWorkDays()
This method updates the working time of all personnel.
### 7-2-  writeToFile Method
    private static void writeToFile(String fileName, List<String> content)
This method updates the working hours updated in the department and general personnel files to which it belongs.
## 8-**ProfileUpdater class**
I don't want to mess with the timer, let it update the running time every time I enter the program, if you want, you can use this class. It should be remembered that the ProfileUpdater class is used in this class.

---  
  
### Conclusion:  
 
To summarize, this project is a management system project written for all employees of a textile company to use.
