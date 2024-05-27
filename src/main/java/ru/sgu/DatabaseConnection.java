package main.java.ru.sgu;


import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        // JDBC connection parameters
        String url = "jdbc:postgresql://localhost/work"; // URL to your PostgreSQL database
        String user = "postgres"; // Username 'admin'
        String password = "admin"; // Password 'admin'

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Query 1: Employees whose age is greater than 20
            String query1 = "SELECT employeeName FROM employeesAge WHERE age > 20";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            ResultSet resultSet1 = statement1.executeQuery();
            System.out.println("Employees whose age is greater than 20:");
            while (resultSet1.next()) {
                System.out.println(resultSet1.getString("employeeName"));
            }
            resultSet1.close();
            statement1.close();

            // Query 2: Average salary per department
            String query2 = "SELECT departmentName, AVG(salary) AS averageSalary FROM departmentSalary GROUP BY departmentName";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            ResultSet resultSet2 = statement2.executeQuery();
            System.out.println("\nAverage salary per department:");
            System.out.println("departmentName\taverageSalary");
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString("departmentName") + "\t" + resultSet2.getDouble("averageSalary"));
            }
            resultSet2.close();
            statement2.close();

            // Query 3: Employees' department and location
            String query3 = """
                    SELECT employeeName, departmentName, location
                                    FROM departmentEmployee as t1, departmentLocation as t2
                                    WHERE t1.departmentId = t2.departmentId;""";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            ResultSet resultSet3 = statement3.executeQuery();
            System.out.println("\nEmployees' department and location:");
            System.out.println("employeeName\tdepartmentName\tlocation");
            while (resultSet3.next()) {
                System.out.println(resultSet3.getString("employeeName") + "\t" +
                        resultSet3.getString("departmentName") + "\t" +
                        resultSet3.getString("location"));
            }
            resultSet3.close();
            statement3.close();

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
