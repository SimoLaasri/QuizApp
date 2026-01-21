# QuizApp - Copilot Instructions

## Project Overview

QuizApp is a Java Swing desktop application for creating and taking quizzes, designed for both students (étudiants) and teachers (enseignants). The application uses MySQL for data persistence and follows the DAO (Data Access Object) architectural pattern.

**Technology Stack:**
- **Language**: Java 17 (JDK 17 or higher required)
- **GUI Framework**: Java Swing
- **Database**: MySQL 5.7 or higher
- **Build Tool**: Apache Ant (NetBeans project)
- **JDBC Driver**: mysql-connector-j-9.0.0.jar (located in `lib/` directory)

## Build and Development Instructions

### Database Setup (CRITICAL - MUST BE DONE FIRST)

**ALWAYS ensure MySQL is installed and running before building or running the application.**

1. **Create the database and schema:**
   ```bash
   mysql -u root -p < database/schema.sql
   ```
   This creates the `quizapp` database with tables: `utilisateur`, `quiz`, `question`, `tentative`, and inserts sample test data.

2. **Default database credentials** (configured in `src/DAO/DBConnection.java`):
   - URL: `jdbc:mysql://localhost:3306/quizapp`
   - User: `root`
   - Password: `` (empty string)
   
   **If your MySQL setup differs, update `DBConnection.java` before building.**

### Build Instructions

**Method 1: Using Apache Ant (Recommended)**

```bash
# Clean previous builds
ant clean

# Compile the project
ant compile

# Build JAR file
ant jar

# Clean and build in one command
ant clean jar
```

The JAR file will be created at: `dist/QuizApp.jar`

**Method 2: Using NetBeans IDE**

1. Open the project in NetBeans (File > Open Project)
2. Clean and Build: Press `F11` or right-click project > Clean and Build
3. Run: Press `F6` or right-click project > Run

**IMPORTANT:** The project uses Java 17 (`javac.source=17` and `javac.target=17` in `nbproject/project.properties`). Ensure your JDK version matches.

### Running the Application

**From JAR file:**
```bash
java -jar dist/QuizApp.jar
```

**From NetBeans:**
Press `F6` or click Run Project

**Main class:** `quizapp.QuizApp`

### Testing

**Test accounts** (created by `database/schema.sql`):

Teacher Account:
- Username: `prof`
- Password: `prof123`

Student Account:
- Username: `etudiant1`
- Password: `etu123`

**Manual Testing Steps:**
1. Launch the application
2. Login with a test account
3. For teachers: Create quizzes, add questions
4. For students: Take quizzes, view scores

**Note:** This project does not have automated unit tests. All testing is manual through the GUI.

### Common Build Issues and Solutions

**Issue 1: MySQL Connection Failures**
- **Error:** `java.sql.SQLException: Access denied for user 'root'@'localhost'`
- **Solution:** Update credentials in `src/DAO/DBConnection.java` to match your MySQL setup

**Issue 2: Missing JDBC Driver**
- **Error:** `java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver`
- **Solution:** Ensure `lib/mysql-connector-j-9.0.0.jar` exists and is included in the classpath (defined in `nbproject/project.properties`)

**Issue 3: Database Does Not Exist**
- **Error:** `Unknown database 'quizapp'`
- **Solution:** Run `mysql -u root -p < database/schema.sql` to create the database

## Project Architecture and Layout

### Directory Structure

```
QuizApp/
├── .github/                    # GitHub configuration (Copilot instructions)
├── build/                      # Build output (generated, not in Git)
├── dist/                       # Distribution JAR (generated, not in Git)
├── lib/                        # External libraries
│   └── mysql-connector-j-9.0.0.jar  # MySQL JDBC driver
├── database/
│   └── schema.sql             # Database schema and sample data
├── nbproject/                 # NetBeans project configuration
│   ├── build-impl.xml         # Ant build implementation
│   ├── project.properties     # Build properties (Java 17, classpath)
│   └── project.xml            # NetBeans project metadata
├── src/
│   ├── DAO/                   # Data Access Objects (database layer)
│   │   ├── DBConnection.java        # Database connection singleton
│   │   ├── UtilisateurDAO.java      # User CRUD operations
│   │   ├── QuizDAO.java             # Quiz CRUD operations
│   │   ├── QuestionDAO.java         # Question CRUD operations
│   │   └── TentativeDAO.java        # Quiz attempt CRUD operations
│   ├── metier/                # Business model/domain classes
│   │   ├── Utilisateur.java         # User entity
│   │   ├── Quiz.java                # Quiz entity
│   │   ├── Question.java            # Question entity
│   │   ├── Tentative.java           # Quiz attempt entity
│   │   └── RoleUtilisateur.java     # User role enum (ETUDIANT, ENSEIGNANT)
│   ├── ihm/                   # GUI/User Interface (IHM = Interface Homme-Machine)
│   │   ├── FenetreLogin.java        # Login window
│   │   ├── FenetreEtudiant.java     # Student dashboard
│   │   ├── FenetreEnseignant.java   # Teacher dashboard
│   │   ├── FenetreQuiz.java         # Quiz taking interface
│   │   ├── FenetreCreationQuiz.java # Quiz creation interface
│   │   └── FenetreListeQuiz.java    # Quiz list view
│   ├── model/                 # (May contain additional model classes)
│   └── quizapp/
│       └── QuizApp.java       # Main entry point (launches FenetreLogin)
├── build.xml                  # Ant build configuration
├── manifest.mf                # JAR manifest
├── .gitignore                 # Git ignore rules
└── README.md                  # Project documentation
```

### Architecture Pattern: DAO (Data Access Object)

The application follows a three-tier architecture:

1. **Presentation Layer (ihm/)**: Java Swing GUI components
   - All GUI classes extend `javax.swing.JFrame`
   - Named with "Fenetre" prefix (French for "Window")

2. **Business Layer (metier/)**: Domain entities/POJOs
   - Plain Java objects representing database entities
   - No database logic, just getters/setters

3. **Data Access Layer (DAO/)**: Database operations
   - Each DAO class corresponds to a database table
   - All database queries are executed here
   - Uses JDBC for database connectivity

### Key Files to Understand

**Entry Point:**
- `src/quizapp/QuizApp.java` - Contains `main()` method, launches `FenetreLogin`

**Database Configuration:**
- `src/DAO/DBConnection.java` - Database connection singleton (UPDATE THIS for custom MySQL configs)
- `database/schema.sql` - Complete database schema and test data

**Build Configuration:**
- `build.xml` - Ant build script (imports nbproject/build-impl.xml)
- `nbproject/project.properties` - Java version (17), classpath, main class
- `manifest.mf` - JAR manifest

### Database Schema

**Tables:**
- `utilisateur` - Users (students and teachers)
  - Fields: id, username, password, nom, prenom, role (ETUDIANT/ENSEIGNANT)
- `quiz` - Quizzes
  - Fields: id, titre
- `question` - Questions for each quiz
  - Fields: id, quiz_id (FK), enonce, choixA-D, bonneReponse (0-3)
- `tentative` - Quiz attempts/results
  - Fields: id, etudiant_id (FK), quiz_id (FK), score, date_tentative

**Foreign Key Relationships:**
- question.quiz_id → quiz.id (CASCADE DELETE)
- tentative.etudiant_id → utilisateur.id
- tentative.quiz_id → quiz.id

## Language and Naming Conventions

**The application uses French naming:**
- Classes and variables use French names: `Utilisateur` (User), `Enseignant` (Teacher), `Étudiant` (Student), `Tentative` (Attempt), etc.
- Database column names: `nom` (lastname), `prenom` (firstname), `enonce` (statement/question), etc.
- When making changes, **maintain French naming consistency**

## Validation and Pre-Commit Checks

**Before committing code changes:**

1. **Build successfully:**
   ```bash
   ant clean jar
   ```
   
2. **Test database connection:**
   - Ensure MySQL is running: `mysql -u root -p -e "USE quizapp;"`
   
3. **Manual GUI test:**
   - Run the application: `java -jar dist/QuizApp.jar`
   - Login with test accounts
   - Verify no exceptions in console

**No automated CI/CD:** This project does not have GitHub Actions or continuous integration pipelines. All validation is manual.

## Working with the Codebase

### Making Changes to GUI (ihm/)

- All GUI classes use Java Swing components
- NetBeans GUI Builder may have been used (check for `.form` files)
- When modifying GUI, test by running the application and manually interacting

### Making Changes to Database Layer (DAO/)

- Always update corresponding methods in DAO classes when database schema changes
- Test database operations by running the application and verifying data persistence
- **NEVER hardcode database credentials** - keep them in `DBConnection.java`

### Adding New Features

- Follow the existing DAO pattern
- Create entity class in `metier/`
- Create DAO class in `DAO/`
- Create GUI class in `ihm/`
- Update database schema in `database/schema.sql`

## Important Notes for AI Agents

1. **Database First:** The application REQUIRES MySQL to be running and the database to be set up before it can function. Always verify this before testing.

2. **No Unit Tests:** This project does not have automated tests. Rely on manual testing through the GUI.

3. **French Language:** Maintain French naming conventions in code and database. Don't translate variable/class names to English.

4. **NetBeans Project:** This is a NetBeans project using Ant. Don't convert to Maven/Gradle unless explicitly requested.

5. **Java Swing:** This is a desktop GUI application, not a web application. Changes must be made to Swing components.

6. **Build Before Run:** Always build the project with `ant clean jar` before running to ensure all changes are compiled.

7. **Trust These Instructions:** The information provided here has been validated. Only perform additional searches if you find errors or gaps in these instructions.
