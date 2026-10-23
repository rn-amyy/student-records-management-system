import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        try {
            Connection connection =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Connected to MySQL successfully");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public static void saveStudent(Student student) {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        try {
            Connection connection =
                    DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO students" +
                    "(matricNo,fullName,dept,email,gender,regNo,cgpa,level)" +
                    "VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.matricNo);
            statement.setString(2, student.fullName);
            statement.setString(3, student.dept);
            statement.setString(4, student.email);
            statement.setString(5, student.gender);
            statement.setString(6, student.regNo);
            statement.setDouble(7, student.cgpa);
            statement.setInt(8, student.level);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student saved successfully!");
            }
            statement.close();
            connection.close();

    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) {
            System.out.println("A student with that matriculation or registration number already exists."
            );
        } else {
            System.out.println("Unable to save student.");
            e.printStackTrace();
        }
    }

}

    public static void viewAllStudents() {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");

        String sql = "SELECT * FROM students";
        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery()
        ) {
            boolean found = false;

            while (resultSet.next()) {
                found = true;
                System.out.println("-------------------------");
                System.out.println("Matric No: " +
                        resultSet.getString("matricNo"));
                System.out.println("Full Name: " +
                        resultSet.getString("fullName"));
                System.out.println("Department: " +
                        resultSet.getString("dept"));
                System.out.println("Email: " +
                        resultSet.getString("email"));
                System.out.println("Gender: " +
                        resultSet.getString("gender"));
                System.out.println("Reg No: " +
                        resultSet.getString("regNo"));
                System.out.println("CGPA: " +
                        resultSet.getBigDecimal("cgpa"));
                System.out.println("Level: " +
                        resultSet.getInt("level"));
            }

            if (!found) {
                System.out.println("No student records found.");
            }

        } catch (SQLException e) {
            System.out.println("Unable to retrieve students.");
            e.printStackTrace();
        }
    }

    public static void searchStudent(String matricNo) {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        String sql = "SELECT * FROM students WHERE matricNo = ?";

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, matricNo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Student found!");
                    System.out.println("-------------------------");
                    System.out.println("Matric No: " +
                            resultSet.getString("matricNo"));
                    System.out.println("Full Name: " +
                            resultSet.getString("fullName"));
                    System.out.println("Department: " +
                            resultSet.getString("dept"));
                    System.out.println("Email: " +
                            resultSet.getString("email"));
                    System.out.println("Gender: " +
                            resultSet.getString("gender"));
                    System.out.println("Reg No: " +
                            resultSet.getString("regNo"));
                    System.out.println("CGPA: " +
                            resultSet.getBigDecimal("cgpa"));
                    System.out.println("Level: " +
                            resultSet.getInt("level"));
                } else {
                    System.out.println("No student found with that matric number.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to search for student.");
            e.printStackTrace();
        }
    }

    public static void updateStudent(String matricNo, String newEmail) {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        String sql = "UPDATE students SET email=? WHERE matricNo=?";

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, newEmail);
            statement.setString(2, matricNo);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student email updated successfully");
            } else {
                System.out.println("No student found with that matric number.");
            }

        } catch (SQLException e) {
            System.out.println("Unable to update student email.");
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String matricNo) {
        String url = "jdbc:mysql://localhost:3306/student_records";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        String sql = "DELETE FROM students WHERE matricNo=?";

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, matricNo);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with that matric number.");
            }


        } catch (SQLException e) {
            System.out.println("Unable to delete student.");
            e.printStackTrace();
        }
    }
}
