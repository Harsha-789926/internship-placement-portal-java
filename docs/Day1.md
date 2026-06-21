Here is your complete, clean, and neatly formatted **`day_1.md`**. It keeps everything simple, clear, and perfectly organized so it is easy to read.

---

# 🚀 Day 1: Project Setup & Model Layer Completion

## 📌 Project Overview

* **Project Name:** Internship & Placement Tracking Portal (Java Console Application)
* **Objective:** Set up a clean project structure, configure Git/GitHub with a development branch, and design the core data models using Object-Oriented Programming (OOP).

---

## 🛠️ Activities Completed

### 1. Project Folder Structure

The project is divided into distinct folders (packages) to keep the code organized and easy to maintain.

```text
internship-placement-portal-java
│
├── src
│   ├── model/              # Core data blueprints (User, Student, etc.)
│   ├── service/            # Business logic and data management
│   ├── util/               # Input helper utilities
│   └── Main.java           # Entry point to run the app
│
├── docs/                   # Progress documentation (day_1.md, day_2.md)
├── README.md               # Project overview landing page
└── .gitignore              # Files to ignore in Git (like compiled files)

```

* **Benefits:** Neat organization, easy to expand, and aligns with professional industry standards.

---

### 🌿 2. Git & GitHub Configuration

Instead of working directly on the final code line, we set up a professional branching strategy to isolate active work.

* **`main` Branch:** Holds stable, fully tested, production-ready code.
* **`development` Branch:** The active workspace where code is currently written and updated.
* **Benefits:** Prevents broken or incomplete code from ruining a stable version, leaving a clean development history.

---

### 📅 3. Step-by-Step Commit Strategy

Instead of making one massive commit at the end, features were saved incrementally to track progress cleanly:

```text
[Initial Setup] ➔ [Add User] ➔ [Add Student] ➔ [Add Admin] ➔ [Add Job] ➔ [Add Application]

```

* **Benefits:** Easier tracking, painless debugging if an error occurs, and a highly professional GitHub contribution graph.

---

### 🧱 4. Domain Model Design

The core system components were designed using Java classes. Inheritance is used so child classes (`Student` and `Admin`) can reuse shared properties from the parent class (`User`).

| Model Class | Inheritance Relation | Primary Responsibility |
| --- | --- | --- |
| **`User`** | *Base Parent Class* | Stores basic details (`id`, `name`) shared by all users. |
| **`Student`** | *Child of `User*` | Extends user data to track `branch`, `cgpa`, and `skills`. |
| **`Admin`** | *Child of `User*` | Extends user data to track the campus `department`. |
| **`Job`** | *Independent Class* | Holds job opening details (`title`, `company`, `skillsRequired`). |
| **`Application`** | *Independent Class* | Links a `Student ID` to a `Job ID` and tracks the application `status`. |

---

### 🧠 5. Object-Oriented Programming (OOP) Concepts Used

* **Classes & Objects:** Blueprints representing real-world portal entities.
* **Constructors:** Used via the `new` keyword to initialize data when an object is created.
* **Encapsulation:** Making variables `private` and accessing them safely using public getters (`getId()`, `getName()`).
* **Inheritance:** Using `extends` and `super()` to automatically pass down shared identity features from `User` to `Student` and `Admin`.

---

### 🧹 6. Repository Optimization (`.gitignore`)

A `.gitignore` file was added to ensure compiled binary files (`.class` files) are never pushed to GitHub.

* **Benefits:** Keeps the online repository clean, lightweight, and professional.

---

### 🧪 7. Code Verification (`Main.java`)

This testing suite runs in `Main.java` to make sure all classes compile correctly and print out under their respective neatly formatted headers:

```java
import model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Testing Day 1 Architecture Verification\n");

        // 1. Verify Application Relationship Layer
        Application app = new Application(1, 1);
        app.displayApplication();
        System.out.println();

        // 2. Verify Job Blueprint Creation
        Job job = new Job(1, "Software Engineer", "Tech Corp", "Java, Python");
        job.displayJob();
        System.out.println();

        // 3. Verify Student OOP Inheritance Chain
        Student s1 = new Student(1, "Alice", "Computer Science", 3.8, "Java, Python");
        s1.displayStudent();

        // 4. Verify Standalone Parent Structure
        User user = new User(1, "Alice");
        user.displayStandaloneUser();
        System.out.println();

        // 5. Verify Admin OOP Inheritance Chain
        Admin a1 = new Admin(100, "Jevita k", "Placement cell");
        a1.displayAdmin();
    }
}

```

---

## 📈 Key Learnings

* Setting up multi-package Java applications.
* Managing features across remote Git branches (`git checkout -b development`).
* Controlling variables safely using access modifiers (`private`, `protected`).
* Splitting complex console print statements gracefully across parent and child scopes.

---

## 🎯 Final Milestone Checklist

* [x] Created the layered project directory structure.
* [x] Initialized Git and linked it to the GitHub repository.
* [x] Switched cleanly to the active `development` branch.
* [x] Completed all core data model implementations.
* [x] Verified individual entity console print statements.
* [x] Kept repository clean from binary clutter via `.gitignore`.