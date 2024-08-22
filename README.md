# Facebook Marketplace Automation

This project automates and tests Facebook Marketplace using Selenium WebDriver with Java.

## Features

- Automates login to Facebook
- Performs searches in Facebook Marketplace
- Validates search results

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- ChromeDriver (Managed by WebDriverManager)
- Selenium WebDriver

### Setup

1. Clone this repository.


2. Create a `config.properties` file in the "src" directory with your Facebook credentials:

    ```properties
    username=your_facebook_username
    password=your_facebook_password
    ```

    **Note:** The `config.properties` file is ignored by Git and should not be committed to the repository.
   
3. Add the following dependencies to your `pom.xml` file if they are not already present:
   - Selenium WebDriver
   - JUnit
   - WebDriverManager
