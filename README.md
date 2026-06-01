# LaravelTestAutomation

> **Historical reference project.** Implemented as a take-home assignment in 2021 to demonstrate REST API test automation using Java, Cucumber BDD, and Rest Assured.

---

## Overview

A Java-based BDD test automation framework for a REST API, covering functional, negative, and basic stress scenarios.

| | |
|---|---|
| **Language** | Java (Maven) |
| **Framework** | Cucumber 6 + Rest Assured 4 |
| **Test Runner** | JUnit 4 |
| **Reporting** | Cucumber HTML Report |

---

## Project Structure

```
src/
├── main/java/
│   ├── pojo/              # Request/response POJO classes
│   └── java_resources/    # API endpoints (enum), payloads, external data
└── test/java/
    ├── features/          # Gherkin feature files
    ├── step_definitions/  # Cucumber step definitions
    ├── test_resources/    # Request/response specs, utilities
    └── cucumber_options/  # Test runner configuration
```

---

## Test Categories

| Tag | Purpose |
|---|---|
| `@Smoke` | Functional happy-path tests |
| `@Stress` | Basic response-time assertions |
| `@Negative` | Invalid input / error handling |
| `@InvalidData` | Malformed payloads |
| `@NoAuth` | Unauthenticated requests |
| `@InvalidEndPoint` | Non-existent endpoints |

---

## Running Tests

```bash
# All tests
mvn clean test

# By tag
mvn test -Dcucumber.options="--tags @Smoke"
mvn test -Dcucumber.options="--tags @Negative"
```

Reports are generated at `target/cucumber-reports.html`.

---

## Execution Screenshot

![Test Report Screenshot](https://github.com/aliboztemir/LaravelTestAutomation/blob/main/screenshots/TestReportScreenShot.png)
