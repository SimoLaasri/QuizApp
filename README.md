# QuizApp

A Java Swing application for creating and taking quizzes, designed for both students and teachers.

## Description

QuizApp is a quiz management system that allows:
- **Teachers (Enseignants)** to create quizzes with multiple-choice questions
- **Students (Étudiants)** to register, take quizzes, and view their scores
- Automatic score calculation and result tracking
- **NEW**: Student self-registration system
- **NEW**: Quiz timer with countdown and auto-submit

## Requirements

- **Java**: JDK 17 or higher
- **MySQL**: 5.7 or higher
- **NetBeans IDE**: (recommended for development)

## Database Setup

1. Ensure MySQL is installed and running on your system
2. Import the database schema:
   ```bash
   mysql -u root -p < database/schema.sql
   ```
3. The script will:
   - Create the `quizapp` database
   - Create all necessary tables (utilisateur, quiz, question, tentative)
   - Insert sample test data (teacher and student accounts)

## Configuration

Update database connection settings in `src/DAO/DBConnection.java` if needed:
```java
private static final String URL = "jdbc:mysql://localhost:3306/quizapp";
private static final String USER = "root";
private static final String PASSWORD = "";
```

## How to Run

### Using NetBeans
1. Open the project in NetBeans
2. Clean and build the project (F11)
3. Run the project (F6)

### Using Command Line
```bash
# Build the project
ant clean jar

# Run the application
java -jar dist/QuizApp.jar
```

## Default Test Accounts

After running the database schema, you can login with:

**Teacher Account:**
- Username: `prof`
- Password: `prof123`

**Student Account:**
- Username: `etudiant1`
- Password: `etu123`

**Or create a new student account using the registration feature!**

## Project Structure

```
QuizApp/
├── src/
│   ├── DAO/              # Data Access Objects
│   │   ├── DBConnection.java
│   │   ├── UtilisateurDAO.java
│   │   ├── QuizDAO.java
│   │   ├── QuestionDAO.java
│   │   └── TentativeDAO.java
│   ├── metier/           # Business Model Classes
│   │   ├── Utilisateur.java
│   │   ├── Quiz.java
│   │   ├── Question.java
│   │   ├── Tentative.java
│   │   └── RoleUtilisateur.java
│   ├── ihm/              # User Interface (IHM = Interface Homme-Machine)
│   │   ├── FenetreLogin.java
│   │   ├── FenetreInscription.java        # NEW: Student registration
│   │   ├── FenetreEtudiant.java
│   │   ├── FenetreEnseignant.java
│   │   ├── FenetreQuiz.java               # UPDATED: Now with timer
│   │   ├── FenetreCreationQuiz.java
│   │   └── FenetreListeQuiz.java
│   └── quizapp/
│       └── QuizApp.java  # Main entry point
├── database/
│   └── schema.sql        # Database schema and test data
└── build.xml             # Ant build file
```

## Features

### For Students
- **Self-Registration**: Create a student account without admin intervention
- Login to the system
- Select and take available quizzes
- **Timed Quizzes**: Complete quizzes with a countdown timer (30 seconds per question)
  - Visual timer display showing remaining time
  - Warning at 30 seconds remaining
  - Automatic submission when time expires
- View immediate score after completing a quiz
- Multiple-choice questions with A, B, C, D options
- Navigate back and forth between questions

### For Teachers
- Login to the system
- Create new quizzes with custom titles
- Add multiple questions to each quiz
- Specify correct answers for each question
- View list of all available quizzes

### Registration System
The new student registration system includes:
- Simple registration form with username, password, name fields
- Password confirmation validation
- Username uniqueness checking
- Automatic role assignment as "ÉTUDIANT" (Student)
- Direct navigation from login screen
- Immediate feedback on success or errors

### Quiz Timer
Quizzes now feature an integrated countdown timer:
- **Time Allocation**: 30 seconds per question (configurable)
- **Visual Display**: Red timer showing MM:SS format
- **Warning System**: Alert when 30 seconds remain
- **Auto-Submit**: Quiz automatically submits when time expires
- **Prominent Display**: Timer visible at all times during quiz

## Technical Details

- **Language**: Java
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Architecture**: DAO Pattern (Data Access Object)
- **Build Tool**: Apache Ant

## License

This project is created for educational purposes.
