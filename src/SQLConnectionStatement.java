import java.sql.*;

// the parking_lots table has these props
//parking_lots(number INT(2), state char, color char,is_reserved boolean, username char, carID char, type char, parkingDate datetime);


public class SQLConnectionStatement {
    Statement statement;
    Connection connection;

    SQLConnectionStatement() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Parking", "root", "root");
            this.statement = this.connection.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getStatments(String query) {
        try {
            ResultSet rs = this.statement.executeQuery(query);
            this.statement = connection.createStatement();
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void updateStatments(String query) {
        try {
            this.statement.executeUpdate(query);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void close() {
        try {
            this.connection.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
