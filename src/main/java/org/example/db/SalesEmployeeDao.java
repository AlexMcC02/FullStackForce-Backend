package org.example.db;

import org.example.cli.DeliveryEmployeeRequest;
import org.example.cli.SalesEmployee;
import org.example.cli.SalesEmployeeRequest;

import java.sql.*;
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

    public int createSalesEmployee(SalesEmployeeRequest salesEmp) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO Employee (first_name, last_name, salary, bank_account, national_insurance) VALUES " +
                "(?,?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, salesEmp.getFirstName());
        st.setString(2, salesEmp.getLastName());
        st.setDouble(3, salesEmp.getSalary());
        st.setString(4, salesEmp.getBankAccount());
        st.setString(5, salesEmp.getNationalInsurance());


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        int emp_id = 0;

        if(rs.next()){
            emp_id = rs.getInt(1);
        } else {
            throw new SQLException();
        }

        //Add Sales Employee

        insertStatement = "INSERT INTO Sales_Employee (sales_id, commission_rate) VALUES " +
                "(?,?)";

        st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1,emp_id);
        st.setDouble(2,salesEmp.getCommission_rate());

        st.executeUpdate();

        return emp_id;

    }

    public void updateSalesEmployee(int id, SalesEmployeeRequest salesEmployee) throws SQLException {

        Connection c = DatabaseConnector.getConnection();

        String inputStatementEmployee = "UPDATE Employee SET first_name = ?, last_name = ?, salary = ?," +
                " bank_account = ?, national_insurance = ? WHERE emp_id = " + id + ";";

        PreparedStatement stEmployee = c.prepareStatement(inputStatementEmployee, Statement.RETURN_GENERATED_KEYS);

        stEmployee.setString(1, salesEmployee.getFirstName());
        stEmployee.setString(2, salesEmployee.getLastName());
        stEmployee.setDouble(3, salesEmployee.getSalary());
        stEmployee.setString(4, salesEmployee.getBankAccount());
        stEmployee.setString(5, salesEmployee.getNationalInsurance());

        stEmployee.executeUpdate();

        String inputStatementDeliveryEmployee = "UPDATE Sales_Employee SET commission_rate = ? WHERE sales_id = " + id + ";";

        PreparedStatement stDeliveryEmployee = c.prepareStatement(inputStatementDeliveryEmployee, Statement.RETURN_GENERATED_KEYS);
        stDeliveryEmployee.setDouble(1, salesEmployee.getCommission_rate());

        stDeliveryEmployee.executeUpdate();
    }

    public void deleteSalesEmployee(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();

        String deleteStatement1 = "DELETE FROM Sales_Employee WHERE sales_id = ?";

        PreparedStatement st1 = c.prepareStatement(deleteStatement1);

        st1.setInt(1,id);

        st1.executeUpdate();

        String deleteStatement2 = "DELETE FROM Employee WHERE emp_id = ?";

        PreparedStatement st2 = c.prepareStatement(deleteStatement2);

        st2.setInt(1,id);

        st2.executeUpdate();

        c.close();
    }

}
