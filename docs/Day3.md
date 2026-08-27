# Internship & Placement Tracking Portal
## Development Documentation

# Day 3 - Company Module Development
## Objective

The objective of Day 3 was to develop the basic Company module and connect
it with the existing Core Java project structure.


## 1. Company Model

A Company model was created to represent companies participating in the
placement portal.

The Company model currently stores:

- Company ID
- Company Name
- Email
- Location


## 2. Encapsulation

Company attributes were declared private.

Getter methods were created to provide controlled access to company
information.

This maintains the encapsulation structure already followed by the Student,
Job, and Application models.


## 3. Company Constructor

A parameterized constructor was implemented to initialize Company objects.

During the current console-development stage, sample Company objects are
created in Main.java for testing.


## 4. Company Display Functionality

A displayCompany() method was implemented.

It displays:

- Company ID
- Company Name
- Email
- Location

This is currently used to verify company information from the console.


## 5. CompanyService

CompanyService was created to handle company-related operations separately
from Main.java.

An ArrayList<Company> is currently used to store Company objects.

The ArrayList is initialized when a CompanyService object is created.


## 6. Add Company

The addCompany() functionality was implemented.

It receives a Company object and adds it to the companies ArrayList.

This keeps company-management logic inside CompanyService instead of
directly managing the ArrayList from Main.java.


## 7. View Companies

The viewCompanies() functionality was implemented.

The method loops through the companies collection and calls
displayCompany() for every Company object.


## 8. Search Company by ID

A getCompanyById() method was implemented.

The method:

1. Receives a Company ID.
2. Loops through the companies ArrayList.
3. Compares each stored Company ID with the requested ID.
4. Returns the matching Company object when found.
5. Returns null when no matching company exists.


## 9. Object Retrieval Concept

The Company search functionality helped distinguish between creating a new
object and retrieving an existing object.

Creating:

Company c1 = new Company(...)

creates a new Company object.

Retrieving:

Company foundCompany = companyService.getCompanyById(1);

does not create another Company.

It searches for an already existing Company object and stores its reference
in foundCompany.


## 10. Company Module Testing

CompanyService was connected with Main.java.

The following operations were successfully tested:

- Creating a Company
- Adding a Company
- Viewing Companies
- Searching Company by ID
- Handling an existing Company
- Handling a non-existing Company


## 11. Concepts Practiced

The following Java concepts were reinforced:

- Classes and objects
- Constructors
- Encapsulation
- Getter methods
- ArrayList
- Enhanced for loops
- Object references
- Returning objects
- null
- if-else conditions
- Model-Service separation


## 12. Git and GitHub

After completing and testing the Application and Company modules, the
relevant files were staged separately.

The completed work was committed and pushed to the development branch.

The main branch was not merged because active project development is still
being performed on the development branch.


## Day 3 Result

By the end of Day 3, the basic Company module was successfully implemented
and tested.

The current completed project modules are:

- Student Model
- Job Model
- StudentService
- JobService
- RecommendationService
- Application Model
- ApplicationService
- Company Model
- CompanyService

The Application and Company modules have also been integrated and tested
through Main.java.


------------------------------------------------------------


# Current Development Position

Phase 1 - Core Java Foundation
Status: COMPLETED

Phase 2 - Application Module
Status: BASIC VERSION COMPLETED

Phase 2 - Company Module
Status: BASIC VERSION COMPLETED

Phase 2 - Admin Module
Status: NEXT


# Next Development Task

The next development session will focus on:

1. Reviewing the existing Admin model.
2. Completing the Admin model if required.
3. Creating or completing AdminService.
4. Implementing basic Admin operations.
5. Connecting Admin operations with applications.
6. Testing the Admin workflow through Main.java.