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


Employee Tab:
Description: Manager can perform various functions. For example, user level Employee will be added by manager for the project and bug tracking.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Add-Employee.PNG)

Manager can Update and Delete the Employee as well.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Employee-Added.PNG)

Project tab:
Description: Manager/admin can add, update and delete the project from user control tab.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Project.PNG)

Department tab:
Manager and Admin will be having the privileges to add/update/delete department.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Department.PNG)

Bug Tracking tab:
Description: Bug Tracking is managed by both Manager and Employee. Both user will have add/update/delete button enabled in the system.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/BugTrack.PNG)

Report Management
Description: All the user will have the privileges to access the report management.
Manager will add/update or delete the report whereas employee can only view the report system. 
1) For Admin/Manager:
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Report.PNG)
2) For Employee:
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/report-emp.PNG)

Report tab
Report Can be generated on click of print button and xsl icon button.
Jasper Report Generation: with the help of Jasper api, the report generation has been done in this system and it can be saved into desktop as pdf version. The XSL version report also can be downloaded into the desktop.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Report-1.PNG)
Chart Tab
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Chart.PNG)

User-Employee Management
Description: Only Admin and Manager will be having the access to the Employee Management tab. Here the manager can edit the profile of the employee and  also set the permission for it.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/User-Employee.PNG)
Employee can be added from the option.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/Add-Employee-1.PNG)

User-Employee Permission: The manager can set the permission for each individual employee. So when the Employee will logged in first time they can access everything. But the Manager will set the permission which ever functionality is required to be provide.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/User-Permission.PNG)

Setting tab:
My Account
Description: In the setting tab, each user will have the privileges to edit or update the account.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/MyAccount.PNG)

Logout Screen:
Once the user logout, the user will be redirected to the welcome page.
![alt text](https://github.com/AjayMukhi/Bug-Tracking-JAVAFX-Application/blob/master/Images/LogOut.png)















