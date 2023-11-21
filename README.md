# Gym CRM System with Spring

This is a Gym CRM (Customer Relationship Management) system implemented using the Spring framework. The application
manages gym-related entities such as Trainees, Trainers, and Training sessions. It uses an in-memory storage solution
and supports initialization of data from an external file during application startup.

## Features

- **Entities:**
    - Trainee
    - Trainer
    - Training
    - TrainingType
    - User

- **Services:**
    - TraineeService: Supports creating, updating, deleting, and selecting Trainee profiles.
    - TrainerService: Supports creating, updating, and selecting Trainer profiles.
    - TrainingService: Supports creating and selecting Training profiles.

- **Storage:**
    - InMemoryStorage: An in-memory storage solution implemented as a separate Spring bean.

- **Data Initialization:**
    - The application supports initializing data from an external file during startup. The file path is specified using
      a property placeholder in an external property file.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Setup

1. Clone the repository:

   ```bash
   git clone  https://github.com/AsatryanA/Gym.SpringCore.git
    ```
2. Open the project in your IDE.

3. Customize the application.properties file with the path to your data file:

   ```properties
   # Path to the data file
   data.file.path=src/main/resources/data.txt
   ```

4. Run the application.

## Usage

The application provides a command-line interface for interacting with the system. The following commands are supported:

## License

Distributed under the MIT License. See `LICENSE` for more information.
