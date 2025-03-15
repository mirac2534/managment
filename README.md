


# Managment 

  
### Project Introduction: Managment for Textile  
  
This project is a **personnel tracking system** that can be used by a textile company written in  **java**.   

---  
 
 ### Application Workflow:  
## 1- **Main class:**  
- In the Main class, he first checks to see if there are any staff members. If there are no staff, it creates an object from an empty HR department and adds staff. It should be remembered that adding personnel is only accessible to the HR department. If there is a staff member, he asks for his ID and password to log in. If the ID and password are correct, it retrieves the department using the get method and runs the menu belonging to that department. If the ID or password entered is incorrect, it will give a warning and log out of the program.  
 
## 2 -**Profile class**
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




---  
  
### Conclusion:  
 
