# 🧪 AxisApp Automation Testing Project

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-orange)
![TestNG](https://img.shields.io/badge/TestNG-Testing%20Framework-brightgreen)
![Allure](https://img.shields.io/badge/Allure-Reporting-purple)

---

## 📘 Overview
This project implements automated test cases for **Tealium Website** using **Java 21**, **Maven**, and **TestNG**.  
The design follows the **Page Object Model (POM)** to ensure maintainability, scalability, and reusability.  

### ✨ Key Practices:
- **Page Object Model (POM):** Separates test logic from UI interactions.  
- **JavaDoc for complex logic:** Explains core functionality.  
- **Inline comments for simple logic:** Keeps code readable.  
- **Utilities & Listeners:** Centralized helpers for waits, assertions, screenshots, logging, and reporting.  

---

## 🔹 ROI – Why These Tools?
- **Java 21:** Stable, modern, enterprise-ready language.  
- **Maven:** Simplifies dependency management, builds, and test execution.  
- **TestNG:** Flexible configurations, parallel execution, advanced reporting.  
- **Allure Reporting:** Interactive and visual test reports with screenshots & logs.  
- **Faker:** Generates dynamic test data to increase test coverage and avoid hardcoding.  

✅ Together these tools ensure **high ROI** by reducing maintenance costs and enabling scalable test automation.

---

## 📂 Project Structure

AxisApp_Automation_Task/

│── pom.xml # Maven dependencies & build config

│── .gitignore

│── src/

│ ├── main/

│ │ ├── java/

│ │ │ ├── Base/ # Driver Preparing

│ │ │ │ ├── DriverFactory.java

│ │ │ │ └── DriverManager.java

│ │ │ ├── pages/ # Page Object classes

│ │ │ │ ├── CartPage.java

│ │ │ │ ├── CookiesPopup.java

│ │ │ │ ├── HomePage.java

│ │ │ │ ├── ItemDetailsPage.java

│ │ │ │ ├── ItemsPage.java

│ │ │ │ ├── LoginPage.java

│ │ │ │ └── RegisterationPage.java

│ │ │ ├── Utilization/ # Utilities & helpers

│ │ │ │ ├── ActionsUtils.java

│ │ │ │ ├── AllureUtils.java

│ │ │ │ ├── AssertionUtils.java

│ │ │ │ ├── ConfigManager.java

│ │ │ │ ├── FakerUtils.java

│ │ │ │ ├── JavaScriptUtils.java

│ │ │ │ ├── LogUtils.java

│ │ │ │ ├── PropertyFileReader.java

│ │ │ │ ├── ScreenshotsUtils.java

│ │ │ │ ├── TestListener.java

│ │ │ │ └── WaitUtils.java

│ │ └── resources/

│ └── test/ # Test Cases classes

│ │ ├── java/

│ │ │ ├── AddToCartTests.java

│ │ │ ├── LoginTests.java

│ │ │ └── RegistrationTests.java

│ │ ├── resources/

│ │ │ ├── configs.properties

│ │ │ ├── log4j2.xml

│ │ │ └── testng.xml


---

## ⚙️ Setup Instructions

### ✅ Prerequisites
1. Install **Java 21**  
   ```bash
   java -version
---
2. Install **Maven** 

- Download Maven
Go to: https://maven.apache.org/download.cgi
- Under "Files", click the link: apache-maven-3.x.x-bin.zip
-  Wait for the download to complete
- Create a folder: C:\Program Files\Apache\Maven
- Extract the downloaded ZIP file to this location
- Remember the full path (e.g., C:\Program Files\Apache\Maven\apache-maven-3.x.x)
- Set Environment Variables ( Windows -> Search -> Environment Variables)
- Under "System variables", Double click on "Path"
- Add Variable value: C:\Program Files\Apache\Maven\apache-maven-3.x.x\bin
- Click OK to close all windows
- Verify Maven Installation
    ```bash
   mvn -version

---
3. Install **Allure Report for Windows**

Install from Scoop
Allure Report commandline latest version


- In a terminal, run this command:

   ```bash
   scoop install allure
- Run this command to see if it reports the latest version:
   ```bash
  allure --version

---
## ▶️ Running Tests
### Option 1: Run via IDE

- Right-click on testng.xml → Select Run.

### Option 2: Run via Maven
    mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml

---
## 📊 Reporting

After execution, test results are generated under:

    target/allure-results/


By default, TestListener.java automatically serves the Allure Report after test execution using:


    allure serve target/allure-results


⚡ This means you don’t need to manually run Allure — the report will open automatically in your browser when tests finish.
