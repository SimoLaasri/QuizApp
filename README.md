# QuizApp

A Java Swing application for creating and taking quizzes, designed for both students and teachers.

## Description

QuizApp is a quiz management system that allows:
- **Teachers (Enseignants)** to create quizzes with multiple-choice questions
- **Students (Étudiants)** to take quizzes and view their scores
- Automatic score calculation and result tracking

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
│   │   ├── FenetreEtudiant.java
│   │   ├── FenetreEnseignant.java
│   │   ├── FenetreQuiz.java
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
- Login to the system
- Select and take available quizzes
- View immediate score after completing a quiz
- Multiple-choice questions with A, B, C, D options

### For Teachers
- Login to the system
- Create new quizzes with custom titles
- Add multiple questions to each quiz
- Specify correct answers for each question
- View list of all available quizzes

## Technical Details

- **Language**: Java
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Architecture**: DAO Pattern (Data Access Object)
- **Build Tool**: Apache Ant

## License

This project is created for educational purposes.
