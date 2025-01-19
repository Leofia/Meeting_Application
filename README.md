# Personal Appointment/Meeting Book Application

This is a Java-based console application designed to manage personal appointments and meetings. It allows users to create, attend, cancel, and view meetings, providing a simple and effective way to organize their schedules.

## Table of Contents

1.  [Introduction](#1.-Introduction)
2.  [Features](#2.-Features)
3.  [Technologies Used](#3.-Technologies-Used)
4.  [How to Run the Application](#4.-How-to-Run-the-Application)
    *   [Prerequisites](#prerequisites)
    *   [Step-by-Step Instructions](#step-by-step-instructions)
5.  [Project Structure](#5.-Project-Structure)

## 1. Introduction

This project is a personal appointment/meeting book application implemented in Java. The application allows users to manage their meetings, including creating new meetings, attending existing meetings, canceling meetings, and viewing scheduled meetings. This simple console application provides a basic but functional tool for organizing personal schedules.

## 2. Features

*   **User Management:**
    *   Create new users.
    *   Login with existing usernames.
    *   Logout of the current user session.
    *   Prevent login before user creation.
    *   Prevent login with an unknown username.
    *   Prevent new login before current logout.
*   **Meeting Management:**
    *   Create and host new meetings.
    *   Cancel meetings organized by the current user.
    *   Attend existing meetings by name.
    *   Leave meetings the current user is attending by name.
    *   Display meetings the current user is attending.
    *   Display meetings the current user has organized.
*   **Basic Meeting Details:**
    *   Meeting name.
    *   Meeting date and time.
    *   Online or face-to-face meeting type.
    *   Meeting URL for online meetings, and location for face-to-face meetings.
*  **Input Handling**
    * Integer inputs must be valid numbers or there will be an InputMismatchException.
    * Boolean inputs must be true or false or there will be an InputMismatchException.
    * Meeting names must be in the list or an error will be given.

## 3. Technologies Used

*   **Programming Language:** Java
*   **Development Environment:** Any Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)
*   **Build Tool:** The application was built without external build tools.
*   **Data Structure:** ArrayList for storing users and meetings

## 4. How to Run the Application

### Prerequisites

Before you begin, make sure you have the following installed on your system:

*   **Java Development Kit (JDK):** Make sure you have JDK 8 or higher installed. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/downloads/).
*   **Java IDE (Optional):** You can use any Java IDE or a simple text editor to create and modify the Java files.

### Step-by-Step Instructions

1.  **Clone the repository:**
    If you have the project in a GitHub repository, clone it to your local machine using:
    ```bash
    git clone <repository_url>
    ```

2.  **Navigate to project directory:**
     ```bash
     cd <project_directory>
     ```
    Navigate to the project directory where the Java source files are located.

3.  **Compile the Java files:**
    Open a terminal in the project's root folder and compile all java files.
    ```bash
    javac project/*.java
    ```
    This command will compile all the `.java` files in the `project` directory and create `.class` files.


4.  **Use the Application:**

    *   **Create Users:** The application will first ask you to create users. Type a username and press Enter. You can continue entering usernames or type `-1` and press Enter to proceed to the main menu. **Note:** At least one username must be created to proceed to the main menu.
    *  **Login:** After creating users, select `0` and enter your username to login. If you are already logged in, you must logout first before changing users.
    *   **Navigate the Menu:** The menu presents options from 0 to 8. Enter the corresponding number for the desired action:
        *   `0`: Login to the application.
        *   `1`: Create and host a new meeting.
        *   `2`: Cancel a meeting you have organized.
        *   `3`: Attend an existing meeting by name.
        *   `4`: Leave a meeting you are attending by name.
        *   `5`: Display all meetings you are attending.
        *   `6`: Display all meetings you have organized.
        *   `7`: Logout of the current user session.
        *   `8`: Exit the application.
        * Please note that you must be logged in to use menu options from `1` to `7`.
    *   Follow the on-screen instructions to provide necessary information.

## 5. Project Structure

The project's Java code is organized as follows:
project/
├── Test.java # Main application class
├── Person.java # Class to model a person
├── Meeting.java # Class to model a meeting
└── Mdate.java # Class to model date and time
