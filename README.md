A scalable UI Automation Framework built using Java, Selenium WebDriver, TestNG, Cucumber, and Maven, following a Hybrid Architecture combining:

Page Object Model (POM)

Reusable Abstract Components

TestNG Tests & Suites

Cucumber BDD Layer

Listeners & Retry Logic

HTML Reporting (ExtentReports)

This project is designed for real-world automation practice, CI/CD integration, and professional portfolio building.

📁 1. Project Structure
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
│   │               └── resoucre/               # Config & helper files
│   │
│   └── test/
│        └── java/
│             ├── Tepbac/
│             │      ├── Cucumber/             # Cucumber runners
│             │      ├── data/                 # JSON/Excel test data
│             │      └── stepDefinitions/      # StepDefinitionImpl
│             │
│             ├── TestComponents/
│             │      ├── BaseTest              # WebDriver setup/teardown
│             │      ├── Listeners             # Reports, logging
│             │      └── Retry                 # Retry for flaky tests
│             │
│             └── tests/                       # TestNG classes
│
│── testSuites/                # regression.xml, smoke.xml
│── pom.xml
│── .gitignore
└── README.md

⚙️ 2. Technology Stack
Technology	Purpose
Java 8+	Programming language
Selenium WebDriver	UI test automation
TestNG	Runner, reporting, parallel execution
Cucumber	BDD (Given-When-Then)
Maven	Build & dependency management
ExtentReports	HTML test reporting
Git / GitHub	Version control
Jenkins (Optional)	CI/CD pipeline
🧩 3. Framework Highlights
✔ Page Object Model (POM)

Clean separation between page logic and test logic.

✔ Abstract Components

Reusable functions: waits, clicks, JS actions, screenshots.

✔ TestNG Test Structure

testng.xml suite support

parallel execution

assertions & groups

✔ BDD with Cucumber

Readable scenarios mapped to StepDefinitionImpl.

✔ Listeners & Retry

Screenshot on failure

Logging

RetryAnalyzer for unstable tests

✔ ExtentReports

Beautiful HTML reports with logs & screenshots.

▶️ 4. How to Run Tests
Run all tests:
mvn clean test

Run a specific TestNG suite:
mvn clean test -DsuiteXmlFile=testSuites/regression.xml

Run Cucumber tests:
mvn test -Dcucumber.options="--tags @Smoke"

📊 5. Test Reports

Reports generated at:

/reports/


Includes logs, screenshots, and test results.

🔗 6. CI/CD Integration (Optional)

Compatible with Jenkins:

GitHub webhook triggers Jenkins

Jenkins pulls code

Executes Maven tests

Publishes ExtentReport artifacts

🧭 7. Future Enhancements

 Add Jenkinsfile

 Add Allure Report

 Add Docker + Selenium Grid

 Environment-based test configs

 API testing module

👨‍💻 8. Author

Author: Tanduyhoang
Role: QA Automation Engineer
Purpose: Practicing automation framework design & CI/CD integration.
