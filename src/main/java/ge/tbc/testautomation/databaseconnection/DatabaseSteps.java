package ge.tbc.testautomation.databaseconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ge.tbc.testautomation.data.Constants.lockedUserSQL;
import static ge.tbc.testautomation.data.Constants.standardUserSQL;

public class DatabaseSteps {

    public ResultSet getStandardUser() {
        try {
            Statement statement = MSSQLConnection.connect().createStatement();
            return statement.executeQuery(standardUserSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getLockedUser() {
        try {
            Statement statement = MSSQLConnection.connect().createStatement();
            return statement.executeQuery(lockedUserSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
