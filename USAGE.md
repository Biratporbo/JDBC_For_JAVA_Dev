# Usage

This project contains simple JDBC example programs. Quick steps to build and run them on Windows (or any system with Java 11+):

1. Prerequisites
   - Java JDK 11 or newer installed and on your `PATH`.
   - (Optional) A JDBC driver if you plan to connect to an external database.

2. Build

From the repository root run:

```powershell
cd JDBC_Learning
javac -d out src/*.java
```

This compiles the `.java` files into the `out` directory.

3. Run

To run the main example (`MainDB`) or other classes, use:

```powershell
java -cp out MainDB
java -cp out UserManagementSystem
```

Replace the class name with any other example (e.g., `InsertData`, `UpdateData`, `SearchData`, `DeleteData`).

4. Database setup

If the examples require a database schema, run the SQL in the `sql/SetUp.sql` file using your preferred SQL client before running the Java programs.

5. Notes
   - If your examples require a specific JDBC driver JAR, add it to the classpath when running, for example:

```powershell
java -cp "out;path\to\jdbc-driver.jar" MainDB
```

   - If compilation or runtime errors appear, open the offending `.java` file in `src/` and check for package declarations or missing libraries.

If you want, I can also add a `CHANGELOG.md`, `LICENSE.md`, or expand `CONTRIBUTING.md` with contributor guidelines—tell me which one you'd like next.
