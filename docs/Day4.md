# Internship & Placement Tracking Portal
## Day 4 - Admin Module and Application Status Management

---

## 1. Objective

The objective of Day 4 was to complete the basic Admin module and connect
the Admin and Company modules with the existing Application module.

The main focus was to allow Admin and Company services to manage the status
of student job applications while keeping the actual application-related
logic inside ApplicationService.

---

## 2. Review of Existing Admin Model

The project already contained an Admin model, so instead of creating a new
class, the existing model was reviewed and extended.

The Admin class inherits common user information from the User class.

The inheritance relationship is:

User
 |
 +-- Admin

The User class contains common information such as:

- User ID
- User name

The Admin class contains Admin-specific information such as:

- Department

This avoids declaring the same user information again inside Admin.

---

## 3. Admin Inheritance

The Admin class extends the User class.

This allows Admin to inherit the properties and methods available in User.

The Admin constructor uses the parent constructor to initialize the inherited
user information.

Conceptually, the flow is:

Create Admin
    |
    +-- Initialize User information
    |       |
    |       +-- ID
    |       +-- Name
    |
    +-- Initialize Admin information
            |
            +-- Department

This reinforced the concept of inheritance and code reuse in Java.

---

## 4. Admin Getter

A getter method was added for the Admin department.

This provides controlled access to the private department field and
maintains encapsulation.

The User model was also updated with a getter for the user ID.

Because Admin extends User, the Admin object can use the inherited ID getter.

Therefore, AdminService can identify an Admin using the ID inherited from
User.

---

## 5. AdminService

A new AdminService class was created inside the service package.

The purpose of AdminService is to keep Admin-related operations separate
from the Main class and the Admin model.

The service currently manages Admin objects using:

ArrayList<Admin>

This is temporary in-memory storage for the Core Java stage of the project.

Later, this storage will be replaced with database persistence.

---

## 6. Admin ArrayList Initialization

The AdminService constructor initializes the Admin ArrayList whenever an
AdminService object is created.

The flow is:

Create AdminService
       |
       v
Initialize ArrayList<Admin>
       |
       v
Ready to store Admin objects

This prevents Admin storage logic from being handled directly inside
Main.java.

---

## 7. Add Admin Functionality

An addAdmin operation was implemented.

The method receives an Admin object and stores it inside the Admin ArrayList.

Flow:

Admin Object
     |
     v
AdminService
     |
     v
addAdmin()
     |
     v
ArrayList<Admin>

This follows the same service-layer pattern already used for Student,
Job, Company, and Application modules.

---

## 8. View Admin Functionality

A viewAdmin operation was implemented.

The method loops through the Admin ArrayList and displays each stored Admin
using the Admin model's display method.

This functionality was tested from Main.java.

---

## 9. Search Admin by ID

A getAdminById operation was implemented.

The method receives an Admin ID and searches through the Admin ArrayList.

The search flow is:

Receive Admin ID
       |
       v
Loop through Admin objects
       |
       v
Compare Admin ID
       |
       +---- Match ----> Return Admin object
       |
       +---- No Match
               |
               v
          Continue Search
               |
               v
       No Admin Found
               |
               v
          Return null

The ID comparison uses the getId() method inherited from the User class.

---

## 10. Testing AdminService in Main

The basic AdminService flow was connected with Main.java.

The following operations were tested:

- Creating an Admin object
- Creating AdminService
- Adding an Admin
- Viewing Admin information
- Searching Admin by ID
- Handling a found Admin
- Handling the possibility of a missing Admin

The project compiled and executed without errors.

---

## 11. Connecting Admin with ApplicationService

The next objective was to allow the Admin side to update the status of an
application.

ApplicationService already contained the application status update logic.

Therefore, the same logic was not duplicated inside AdminService.

Instead, AdminService delegates the request to ApplicationService.

The architecture is:

Admin
  |
  v
AdminService
  |
  v
ApplicationService
  |
  v
Find Application
  |
  v
Update Status
  |
  v
Application Object

This maintains separation of responsibilities between services.

---

## 12. Admin Application Status Management

AdminService was extended with functionality for requesting an application
status update.

The operation receives:

- ApplicationService reference
- Application ID
- New application status

AdminService then calls the existing update operation available inside
ApplicationService.

For example, an application can move from:

Applied -> Shortlisted

or:

Applied -> Rejected

or:

Shortlisted -> Selected

The actual modification of the Application object remains the responsibility
of ApplicationService.

---

## 13. Application Status Update Trace

The status-management flow can be represented as:

Main
 |
 v
AdminService
 |
 | Request application status update
 |
 v
ApplicationService
 |
 | Search using Application ID
 |
 v
Application Found
 |
 v
Application.setStatus()
 |
 v
Status Updated

For example:

Application ID = 1
Current Status = Applied

Admin requests:

New Status = Selected

The flow becomes:

AdminService
     |
     v
ApplicationService
     |
     v
Find Application ID 1
     |
     v
setStatus("Selected")
     |
     v
Status = Selected

This functionality was successfully tested.

---

## 14. Connecting Company with ApplicationService

The CompanyService was also extended with application status management.

Instead of creating separate application-update logic inside CompanyService,
the CompanyService also delegates the request to ApplicationService.

The architecture is:

Company
   |
   v
CompanyService
   |
   v
ApplicationService
   |
   v
Application
   |
   v
Status Updated

This creates a consistent design for both Admin and Company operations.

---

## 15. Admin and Company Status Management

After today's implementation, both sides can initiate application status
updates.

The overall flow is:

             Application
                 ^
                 |
        ApplicationService
           ^           ^
           |           |
    AdminService   CompanyService
           ^           ^
           |           |
         Admin       Company

ApplicationService remains responsible for managing the Application objects.

AdminService and CompanyService use ApplicationService instead of directly
duplicating application-management logic.

---

## 16. Service Layer Structure After Day 4

The project currently contains the following basic services:

StudentService
     |
     +-- Student management

JobService
     |
     +-- Job management

RecommendationService
     |
     +-- CGPA and skill-based recommendation

ApplicationService
     |
     +-- Application management
     +-- Duplicate application prevention
     +-- Application search
     +-- Status management

CompanyService
     |
     +-- Company management
     +-- Company search
     +-- Application status request

AdminService
     |
     +-- Admin management
     +-- Admin search
     +-- Application status request

---

## 17. Concepts Practiced

The following Java and software-development concepts were practiced during
Day 4:

- Inheritance
- Parent and child classes
- super constructor
- Encapsulation
- Getter methods
- ArrayList
- Enhanced for loops
- Object references
- Returning objects from methods
- null handling
- Service classes
- Method parameters
- Calling methods through objects
- Communication between service classes
- Separation of responsibilities
- Code reuse

---

## 18. Compilation and Testing

After completing the Admin and Company status-management functionality,
the project was compiled and executed.

The completed flow ran without compilation errors.

The following functionality was verified:

- Admin creation
- Admin storage
- Admin display
- Admin search
- Admin application status update
- Company application status update
- Application status display

---

## 19. Git and GitHub

After testing the completed functionality, only the relevant Day 4 files
were staged.

The Day 4 changes included updates related to:

- Main
- Admin model
- User model
- AdminService
- CompanyService

Unrelated utility-file changes were intentionally not included in the
Day 4 commit.

The completed Day 4 work was committed locally and pushed to the
development branch.

Development continues on the development branch, while the main branch
remains unchanged until the project reaches a stable stage.

---

## 20. Day 4 Result

By the end of Day 4, the basic Admin module was successfully completed.

The Admin and Company modules can now use ApplicationService to manage
application status.

The completed Core Java modules currently include:

- Student
- Job
- Company
- Admin
- Application
- StudentService
- JobService
- CompanyService
- AdminService
- ApplicationService
- RecommendationService

The basic Core Java architecture required before database development is
now in place.

---

## 21. Current Project Flow

Student
   |
   v
StudentService
   |
   v
JobService
   |
   v
RecommendationService
   |
   v
Eligible Job
   |
   v
ApplicationService
   |
   v
Application Created
   |
   v
Status = Applied
   |
   +----------------------+
   |                      |
   v                      v
AdminService         CompanyService
   |                      |
   +----------+-----------+
              |
              v
      ApplicationService
              |
              v
        Status Updated
              |
              v
Shortlisted / Rejected / Selected

---

## 22. Day 4 Completion Status

[x] Reviewed existing Admin model
[x] Added Admin department getter
[x] Added User ID getter
[x] Created AdminService
[x] Created ArrayList<Admin>
[x] Added Admin
[x] Viewed Admins
[x] Searched Admin by ID
[x] Tested AdminService in Main
[x] Connected Admin with ApplicationService
[x] Implemented Admin-side application status management
[x] Tested Admin-side status update
[x] Connected Company with ApplicationService
[x] Implemented Company-side application status management
[x] Tested Company-side status update
[x] Compiled and tested project
[x] Committed Day 4 code
[x] Pushed Day 4 code to development branch

---

## 23. Next Development Phase

The basic Core Java implementation is now ready for the next major stage.

Next:

PHASE 3 - DATABASE DESIGN

The upcoming work will include:

- Finalizing database requirements
- Identifying entities
- Designing the Student table
- Designing the Job table
- Designing the Company table
- Designing the Application table
- Defining primary keys
- Defining foreign keys
- Defining relationships
- Creating the ER diagram

This database design will later be implemented using MySQL.