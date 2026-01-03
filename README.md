# MediLink - Emergency Room Triage System

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![JUnit](https://img.shields.io/badge/Tests-JUnit_5-green) ![Status](https://img.shields.io/badge/Status-Active_Development-blue)

**MediLink** is a Java application designed to manage patient intake and triage in an emergency room environment. It uses a **Priority Queue** system to ensure patients are treated based on the severity of their condition rather than just their arrival time.

## Features

* **Smart Triage Logic:** Patients are automatically sorted by severity (`CRITICAL` > `LOW`). If severities match, the patient who arrived earlier is prioritized.
* **Persistent Data:** Patient records are automatically saved to and loaded from a CSV file (`assets/patients.csv`), so data is never lost between sessions.
* **HTML Dashboard:** Generates a read-only HTML table of the current queue and **automatically opens it in your default browser.**
* **Robust Error Handling:** The system gracefully skips corrupt data lines in files without crashing.
* **Safe Data Management:** Uses "Deep Copy" snapshots to prevent user interface operations from accidentally modifying medical records.

## Getting Started

### Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher (Recommend JDK 17 or 21).
* **IDE:** Eclipse, IntelliJ, or VS Code (optional but recommended).

### Installation & Run
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yourusername/medilink.git](https://github.com/yourusername/medilink.git)
    ```
2.  **Compile the code:**
    Navigate to `src` and run:
    ```bash
    javac com/medilink/main/Main.java
    ```
3.  **Run the application:**
    ```bash
    java com.medilink.main.Main
    ```

---

## Usage Guide

When you run the application, you will see the following menu:

1.  **Open Patients Table:** Exports the current list to an HTML file and opens it to view all patients at a glance.
2.  **Get Next Patient:** Retrieves the highest-priority patient from the queue and removes them from the list (simulating a doctor taking the patient).
3.  **Add Patient:** Register a new patient with a Name, ID, and Severity level.
4.  **Change Patient Severity:** Update the condition of an existing patient (e.g., upgrading a patient from `LOW` to `CRITICAL`). This immediately re-sorts the queue.
5.  **Save and Exit:** Saves all current data to `patients.csv` and closes the program.

### Severity Levels
The system supports 4 distinct severity levels (mapped to integer priorities):
1.  **LOW** (Least urgent)
2.  **MODERATE**
3.  **SERIOUS**
4.  **CRITICAL** (Most urgent)


## Project Structure

The code is organized into a modular package structure:

* **`com.medilink.main`**: Contains the entry point (`Main.java`) and menu loop.
* **`com.medilink.service`**: Holds the business logic (`EmergencyRoom.java`), including the priority queue management.
* **`com.medilink.model`**: Defines the data objects (`Patient.java`, `Severity.java`).
* **`com.medilink.fileManagement`**: Handles input/output operations (`CsvReader`, `CsvWriter`, `HtmlManager`).
* **`com.medilink.utils`**: Helper classes for input validation and opening desktop files.

## Testing

This project uses **JUnit 5** for automated testing.
* **Logic Tests:** Verify that high-severity patients always jump to the front of the line.
* **Data Integrity:** Ensures that corrupt CSV lines are identified and skipped without crashing the system.

Run the tests using your IDE's test runner or via command line.

---

## Author
**[Javiperezdev]**
*Developed for learning purposes*