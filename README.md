AutomationCICD – Selenium Java Framework
=====================================================================================

A scalable UI Automation Framework built using **Java, Selenium WebDriver, TestNG, Cucumber, and Maven**, following a **Hybrid Architecture** combining:

-   **Page Object Model (POM)**

-   **Reusable Abstract Components**

-   **TestNG Tests & Suites**

-   **Cucumber BDD Layer**

-   **Listeners & Retry Logic**

-   **HTML Reporting (ExtentReports)**

This project is designed for real-world automation practice, CI/CD integration, and professional portfolio use.

* * * * *

📁 1. Project Structure
-----------------------

The structure below reflects the **actual layout** from your project:
```bash
SeleniumFrameworkDesign
│── .idea/
│── .mvn/
│── reports/                     # ExtentReports HTML outputs
│
│── src/
│   ├── main/
│   │    └── java/
│   │         └── Tepbac/
│   │               ├── AbstractComponents/     # Reusable wrappers & utilities
│   │               ├── pageobjects/            # Page Object Model classes
│   │               └── resoucre/               # Configuration & resource files
│   │
│   └── test/
│        └── java/
│             ├── Tepbac/
│             │      ├── Cucumber/             # Cucumber test runners
│             │      ├── data/                 # Test data (JSON/Excel)
│             │      └── stepDefinitions/      # StepDefinitionImpl
│             │
│             ├── TestComponents/
│             │      ├── BaseTest              # Driver setup / teardown
│             │      ├── Listeners             # ExtentReports, logging
│             │      └── Retry                 # Retry for flaky tests
│             │
│             └── tests/                       # TestNG test classes
│
│── target/
│── testSuites/                # testng.xml test suites
│── pom.xml
│── .gitignore
└── README.md`
```
* * * * *

⚙️ 2. Technology Stack
----------------------

| Technology | Purpose |
| --- | --- |
| **Java 8+** | Programming language |
| **Selenium WebDriver** | UI automation |
| **TestNG** | Test runner, assertions, parallel execution |
| **Cucumber JVM** | BDD with Gherkin (Given-When-Then) |
| **Maven** | Build & dependency management |
| **ExtentReports** | HTML test reporting |
| **Git / GitHub** | Version control |
| **(Optional) Jenkins** | CI/CD pipeline |

* * * * *

🧩 3. Framework Highlights
--------------------------

### ✔ Hybrid Framework Architecture

A combination of POM, reusable components, and BDD for maximum scalability.

### ✔ Page Object Model (POM)

Each application page is represented by a dedicated class containing locators and business functions.

### ✔ Abstract Components

Centralized wrapper utilities:

-   WebDriver waits

-   Screenshot methods

-   Driver utilities

### ✔ TestNG Integration

-   testng.xml suite support

-   Parallel execution

-   Assertions

-   Grouping (smoke, regression)

### ✔ Cucumber BDD Layer

StepDefinitionImpl maps Gherkin steps to automation logic, enabling collaboration between QA / BA / Dev.

### ✔ Listeners & Retry Logic

-   Automatically logs failures

-   Attaches screenshots

-   Retries flaky tests via RetryAnalyzer

### ✔ ExtentReports

Generates detailed HTML reports with timestamps, logs, and screenshots.

* * * * *

▶️ 4. How to Run the Project
----------------------------

### **Run default TestNG suite**

`mvn test`

### **Run with a specific TestNG Suite**

`mvn test -PRegression`
`ErrorValidation`

### **Run Cucumber tests**

`mvn test -PCucumberTest"`

### **Run tests on Edge (default)**

`mvn test`

### **Run tests on Chrome**

`mvn test -PRegression -Dbrowser=chrome`

### **Run tests on Firefox**

`mvn test -PRegression -Dbrowser=Firefox`

### **Run in headless mode**

`mvn test -PRegression -Dbrowser=edgeheadless`

* * * * *

📊 5. Test Reporting (ExtentReports)
------------------------------------

Reports are generated at:

`/reports/`

Each report includes:

-   Pass / Fail / Skip status

-   Execution logs

-   Timestamp

-   Screenshots for failed steps

-   Detailed step-by-step tracking

* * * * *

🔗 6. CI/CD Integration
----------------------------------

This framework is ready for integration with **Jenkins**:

1.  GitHub Webhook triggers Jenkins on each commit

2.  Jenkins pulls the latest code

3.  Executes Maven command:

    `mvn test`

4.  Generates ExtentReport

5.  Publishes report artifacts or sends notifications

This setup enables fully automated testing pipelines.

* * * * *

🧭 7. Future Enhancements
-----------------------------------

To further extend the framework:

-   Add **Jenkinsfile** (declarative pipeline)

-   Add **Docker + Selenium Grid** for distributed parallel execution

-   Introduce **environment profiles** (QA, Staging, Production)

-   Add API automation module (RestAssured)

-   Add database validation layer

* * * * *

👨‍💻 8. Author
---------------

**Author:** *Hoang Nguyen Duy Tan*\
**Role:** QA Automation Engineer\
**Purpose:** Building a fully structured, scalable automation framework for learning, portfolio, and CI/CD practice.
