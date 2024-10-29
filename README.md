## SwagLabs-Selenide-Tests

### Project Overview
SwagLabs-Selenide-Tests is an automated testing project using **Selenide** with **Java** to test the Swag Labs application. This project demonstrates the use of the **Page Object Model (POM)** design with **keyword-driven management** and generates **Allure reports**. Tests run automatically on **GitHub Actions** for each push or pull request to the `dev` branch , which is more integrated and cost effective.


### Contents
- Features
- Prerequisites
- Installation
- Usage
- Running Tests
  - Running Tests Locally
  - Running Tests Automatically on GitHub
- GitHub Actions Workflow

### Features
- Cross-browser testing using Selenide
- POM-based structure with keyword-driven management
- Automatic test execution on GitHub Actions
- Allure reporting for comprehensive test visualization

### Prerequisites

Ensure the following are installed on your system:

- **Java Development Kit (JDK)** v11 or later
- **Maven**
- **Allure**

### Setting Environment Variables

To configure environment variables in **Windows**:

1. **Open Environment Variables**: Click on the Windows icon, type "Env," and select "Edit Environment Variables for your Account."
2. **Set the following Variables**:
  - **JAVA_HOME** → `C:\Program Files\Java\jdk-11`
  - **MAVEN_HOME** → `C:\Program Files\apache-maven-3.9.6`
  - **ALLURE_HOME** → `C:\Users\Admin\scoop\apps\allure\current`
  - **PATH** → Add `C:\Program Files\Java\jdk-11\bin;C:\Program Files\apache-maven-3.9.6\bin;C:\Users\Admin\scoop\shims;`

### Installation

1. **Clone the Project Repository**:

   ```bash
   git clone https://github.com/PersonalNischalPradhan/SwagLabs-Selenide-Tests.git
   ```

   Use a terminal or **Git Bash** to clone, or it can be done from  **IntelliJ IDEA's** Git functionality as well.

2. **Open the Project in IntelliJ IDEA**:
  - Launch **IntelliJ IDEA** (recommended version **2023.3.3** for JDK 11).
  - Select **Open Project** and navigate to the project folder.
  - Open the folder containing `pom.xml`.

   > JDK can also be installed via **IntelliJ IDEA** under **File > Project Structure** if needed.

### Project Structure and Testing Strategy

- **Page Object Model (POM)**: Each application page has a corresponding class in `src/test/java`.
- **Keyword-Driven Management**: Keywords abstract actions (e.g., login) to improve readability and maintainability.

### Usage

#### Running Tests Locally

1. **Install Dependencies**:

   ```bash
   mvn clean install
   ```

2. **Run Tests**:

   ```bash
   mvn test
   ```

3. **Generate Allure Report**:

   ```bash
   allure serve target/allure-results
   ```

#### Running Tests Automatically on GitHub

Tests run automatically on **GitHub Actions** upon pushes or pull requests to the `dev` branch. Reports are uploaded as Allure artifacts.

**GitHub Actions Workflow** (`.github/workflows/ci.yml`):

```yaml
name: CI

on:
  push:
    branches:
      - dev
  pull_request:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up JDK 11
        uses: actions/setup-java@v3
        with:
          java-version: '11'

      - name: Install dependencies
        run: mvn clean install

      - name: Run Tests
        run: mvn test

      - name: Upload Allure Results
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: target/allure-results
```
### Running Tests Manually on GitHub

You can also manually trigger the workflow from the GitHub Actions tab.

Viewing the Results: After the tests run, you can check the GitHub Actions page to see the test results. If tests fail, the workflow will show error logs, and you can download the allure-report from the artifacts section by doing so a allure-report zip file will get downloaded. Inside that file you will have index.html .

Open index.html in a browser , you can see the report .


### Final Note
Please feel free to submit a pull request or shout for any issue.