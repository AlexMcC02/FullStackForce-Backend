package org.example.db;

import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.cli.SalesEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT emp_id, first_name,last_name, username, salary, bank_account, national_insurance"
                + " FROM Delivery_Employee " +
                "INNER JOIN Employee ON Delivery_Employee.delivery_id = Employee.emp_id");

        List<DeliveryEmployee> deliveryEmployeeList = new ArrayList<>();

        while(rs.next()) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account"),
                    rs.getString("national_insurance")
            );

            deliveryEmployeeList.add(deliveryEmployee);

        }
        c.close();
        return deliveryEmployeeList;
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT emp_id,first_name,last_name, username, salary, bank_account, national_insurance"
                + " FROM Delivery_Employee " +
                "INNER JOIN Employee ON Delivery_Employee.delivery_id = Employee.emp_id "
                + "WHERE delivery_id=" + id);

        while (rs.next())
        {
            return new DeliveryEmployee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account"),
                    rs.getString("national_insurance")
            );

        }
        c.close();
        return null;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmp) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO Employee (first_name, last_name, salary, bank_account, national_insurance) VALUES " +
                "(?,?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, deliveryEmp.getFirstName());
        st.setString(2, deliveryEmp.getLastName());
        st.setDouble(3, deliveryEmp.getSalary());
        st.setString(4, deliveryEmp.getBackAccount());
        st.setString(5, deliveryEmp.getNationalInsurance());


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        int emp_id = 0;

        if(rs.next()){
            emp_id = rs.getInt(1);
        } else {
            throw new SQLException();
        }

        //Add Delivery Employee

        insertStatement = "INSERT INTO Delivery_Employee (delivery_id, username) VALUES " +
                "(?,?)";

        st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1,emp_id);
        st.setString(2,deliveryEmp.getUsername());

        st.executeUpdate();

        return emp_id;

    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String inputStatementEmployee = "UPDATE Employee SET first_name = ?, last_name = ?, salary = ?," +
                " bank_account = ?, national_insurance = ? WHERE emp_id = " + id + ";";

        PreparedStatement stEmployee = c.prepareStatement(inputStatementEmployee, Statement.RETURN_GENERATED_KEYS);

        stEmployee.setString(1, delivery_employee.getFirstName());
        stEmployee.setString(2, delivery_employee.getLastName());
        stEmployee.setDouble(3, delivery_employee.getSalary());
        stEmployee.setString(4, delivery_employee.getBackAccount());
        stEmployee.setString(5, delivery_employee.getNationalInsurance());

        stEmployee.executeUpdate();

        String inputStatementDeliveryEmployee = "UPDATE Delivery_Employee SET username = ? WHERE delivery_id = " + id + ";";

        PreparedStatement stDeliveryEmployee = c.prepareStatement(inputStatementDeliveryEmployee, Statement.RETURN_GENERATED_KEYS);
        stDeliveryEmployee.setString(1, delivery_employee.getUsername());

        stDeliveryEmployee.executeUpdate();
    }
    public void deleteDeliveryEmployee (int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String delete_statement = "DELETE FROM Delivery_Employee WHERE delivery_id = ?";

        PreparedStatement st = c.prepareStatement(delete_statement);

        st.setInt (1, id);

        st.executeUpdate();

        String delete_statement2 = "DELETE FROM Employee WHERE emp_id = ?";

        PreparedStatement st2 = c.prepareStatement(delete_statement2);

        st2.setInt (1, id);

        st.executeUpdate();
    }
}