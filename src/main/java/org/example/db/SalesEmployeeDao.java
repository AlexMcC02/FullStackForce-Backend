package org.example.db;

import org.example.cli.SalesEmployee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Employee JOIN Sales_Employee on " +
                "Employee.emp_id = Sales_Employee.sales_id;");

        List<SalesEmployee> salesEmployees = new ArrayList<SalesEmployee>();

        while (rs.next()) {
            SalesEmployee salesEmployee = new SalesEmployee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account"),
                    rs.getString("national_insurance"),
                    rs.getDouble("commission_rate")
            );
            salesEmployees.add(salesEmployee);
        }
        return salesEmployees;
    }

    public SalesEmployee getSalesEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Employee JOIN Sales_Employee on " +
                "Employee.emp_id = Sales_Employee.sales_id WHERE sales_id = " + id + ";");

        while (rs.next()) {
            return new SalesEmployee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account"),
                    rs.getString("national_insurance"),
                    rs.getDouble("commission_rate")
            );
        }
        c.close();
        return null;
    }

}
