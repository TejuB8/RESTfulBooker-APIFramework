# 🧪 RESTfulBooker API Automation Framework

This is a Java-based Test Automation Framework built to test the [RESTful Booker API](https://restful-booker.herokuapp.com/) using **REST-assured**, **TestNG**, and **POJOs**. It includes both individual test cases and complete integration test scenarios.

## Authors

- Tejaswini Borakanavar [linkedin](https://www.linkedin.com/in/tejaswini-borakanavar-2a748b1a4/)

## 📁 Project Structure (with descriptions)

```
src
├── main
│   ├── endpoints
│   │   └── ApiConstants.java          # Holds base URLs and API endpoint paths as constants.
│   ├── modules
│   │   └── PayloadManager.java        # Creates and manages request payloads (like auth or booking).
│   └── pojos
│       ├── Auth.java                  # POJO for Auth request.
│       ├── AuthResponse.java          # POJO for Auth response.
│       ├── Booking.java               # POJO for Booking request.
│       ├── BookingDates.java          # POJO for nested booking dates.
│       └── BookingResponse.java       # POJO for Booking response.
├── test
│   ├── crud
│   │   └── TestCreateBooking.java     # A basic individual test for creating a booking.
│   ├── sample
│   │   └── integrationsample
│   │       └── SetupMethods.java      # Predefined methods to help set up integration test scenarios.
│   ├── tests
│   │   └── integration
│   │       ├── IntegrationFlow1.java  # Create → Delete Booking → Verify
│   │       ├── IntegrationFlow2.java  # Get a Booking from all → Delete it
│   │       ├── IntegrationFlow3.java  # Delete a Booking → Try Updating it (Negative test)
│   │       └── IntegrationFlow4.java  # Create → Update → Verify updated fields
│   └── asserts
│       └── AssertActions.java         # Common assertion methods for response validations.
├── utils                              # Currently not used (placeholder for future utility classes)
│   └── [empty]
└── testng_qa.xml                      # TestNG suite configuration to run QA group scenarios.
```

---

## 🧪 How to Run Tests

- Run via Eclipse: Right-click `testng_qa.xml` → Run As → TestNG Suite
- Each integration flow is grouped logically under the `@Test(priority = X)` tags

---

## 🚀 Features

- REST API automation using REST-assured
- Uses POJOs for request/response body mapping
- Organized integration test flows
- Assertion utility for cleaner validations

---

## 🔜 Upcoming Enhancements

- ✅ Data-driven testing via Excel or property files
- ✅ Allure report integration
- ✅ CI/CD setup using Jenkins




