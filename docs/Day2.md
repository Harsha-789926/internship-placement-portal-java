# Internship & Placement Tracking Portal
## Development Documentation


# Day 2 - Application Module Development

## Objective

The objective of Day 2 was to develop the basic Application module of the
Internship & Placement Tracking Portal.

The Application module is responsible for representing the relationship
between a student and a job when the student applies for a particular
opportunity.


## 1. Application Model

The Application model was completed to represent an individual job
application.

The following information is maintained for each application:

- Application ID
- Student ID
- Job ID
- Application Status

The Application ID uniquely identifies each application.

The Student ID identifies the student who submitted the application.

The Job ID identifies the job for which the student applied.

The status represents the current stage of the application.


## 2. Application Status

The system supports the following basic application states:

- Applied
- Shortlisted
- Rejected
- Selected

Whenever a student initially applies for a job, the application status is
automatically set to "Applied".


## 3. ApplicationService

ApplicationService was developed to separate application-related business
logic from Main.java.

An ArrayList<Application> is currently used to temporarily store
applications during the Core Java development stage.

The ApplicationService currently provides operations for:

- Adding applications
- Viewing applications
- Applying for jobs
- Searching applications
- Updating application status


## 4. Apply for Job Functionality

The apply-for-job functionality was implemented.

The method receives:

- Application ID
- Student ID
- Job ID

The service creates a new Application object and automatically assigns
"Applied" as its initial status.

The newly created application is then stored in the applications collection.


## 5. Application Status Update

Functionality was implemented to update the status of an existing
application.

The system searches for an application using its Application ID.

When the matching application is found, its status can be changed from
Applied to another stage such as:

Applied -> Shortlisted
Applied -> Rejected
Shortlisted -> Selected


## 6. Duplicate Application Prevention

A duplicate application check was introduced.

Before creating a new application, the system searches for an existing
application containing the same:

Student ID + Job ID

If such an application already exists, another application is not created.

This prevents a student from applying multiple times for the same job.


## 7. Application Search

Two application-search concepts were implemented.

### Search using Student ID and Job ID

This is mainly used to determine whether a student has already applied for
a particular job.

### Search using Application ID

This allows the system to retrieve one specific application using its unique
Application ID.

If an application is found, the Application object is returned.

If no matching application exists, null is returned.


## 8. Concepts Practiced

The following Java concepts were practiced during Application module
development:

- Classes and objects
- Constructors
- Encapsulation
- Getters and setters
- ArrayList
- Enhanced for loops
- if-else conditions
- Object references
- Returning objects from methods
- null values
- Method parameters
- Separation of model and service responsibilities


## 9. Application Flow

Student
   |
   v
Apply for Job
   |
   v
ApplicationService
   |
   v
Check Existing Application
   |
   +---- Existing ----> Prevent Duplicate
   |
   +---- Not Existing
             |
             v
       Create Application
             |
             v
       Status = Applied
             |
             v
       Store Application
             |
             v
       Track / Update Status


## Day 2 Result

By the end of Day 2, the basic Application module was successfully
implemented and tested.

The system can now:

- Create applications
- Store applications
- Display applications
- Prevent duplicate applications
- Search applications
- Track application status
- Update application status
