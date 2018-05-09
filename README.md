# Bug-Tracking-JAVAFX-Application
Bug Tracking Management System

The welcome page is having the Login and registration link.For a new user he has to click on the Create an Account link and then do registration.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Welcome.PNG)

Except Administrator, all other users needs to registered into the system.The registration page will asked the user to fill the fields like username ,full name,password and re enter-password,usertype,emailaddress and contact number. The required validation has been done to take care the mandatory fields.Untill these mandatory fields are not provided by user ,the sign up button wouldnot be enabled.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Registration.PNG)

The Login page will ask the user to fill Username, password and user type. 
There are two validation maintained while login into the system.
1) The validation has been done for username and password if not matched to stored values in the database. It will throw the error.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Wecome%20Validation-1.PNG)

2) If Password not matched that is stored in the database.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/welcome-2.PNG)

Admin/Manager Home page- The admin and manager would be having same privileges and view the common home page .The home page will show the how many department,projects user level employees  defects and etc. They are having the privileges to view usercontrol management, report management screen ,user-Employee Screen,Setting Screen in the dashboard.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Home-Admin.PNG)


User Control Management Tab
Under this panel, there are 4 main functionalities where User can work with.
Employee tab, Project tab, Department tab, Bug Track tab.

Description:
1) Admin/Manager: They have the privileges to perform Add/Update/Delete on all the above functionalities.
Manager will perform the onboarding of user level employees into the project and assigned role to them. Based on the role, Bug can be assigned to Developer employee.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/UserControl-Admin.PNG)

2) Employee: For Tester and Developer, only the Bug tracking tab is accessible. They can add/update/delete for Bug tracking functionality.
For all other functionality/tab he can view the records but cannot perform any operation. 
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/UserControl-Employee.PNG)



