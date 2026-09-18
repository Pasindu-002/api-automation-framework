# API Automation Framework

An automated API testing framework built with **Java**, **REST Assured**, **TestNG**, and **Maven**. This project follows a modular architecture to perform scalable, maintainable, and automated tests for RESTful web services.

---

## 🏗️ Project Architecture

```text
AutomationFW/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── constants/       # Framework level constants (API endpoints, status codes)
│   │   │   ├── payload/         # POJO models for request/response serialization
│   │   │   ├── routes/          # API route definitions
│   │   │   └── utilities/       # Utility classes (JSON reader, date generators)
│   │   └── resources/
│   │       ├── config.properties# API base configurations
│   │       └── testdata/        # Static test data JSON files
│   └── test/
│       └── java/
│           ├── base/            # Base test setup and teardown configurations
│           ├── config/          # Configuration readers
│           ├── specifications/  # Request & Response specification builders
│           └── tests/           # Test execution classes
├── pom.xml                      # Maven dependencies and build settings
├── testng.xml                   # Test suite configuration file
└── .gitignore                   # Version control ignore list
