package org.example.db;

import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeRequest;

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

    public int createDeliveryEmployee(DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Delivery_Employee (first_name, last_name, salary, bank_account, national_insurance_number) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, delivery_employee.getFirstName());
        st.setString(2, delivery_employee.getLastName());
        st.setDouble(3, delivery_employee.getSalary());
        st.setString(4, delivery_employee.getNationalInsurance());
        st.setString(5, delivery_employee.getBackAccount());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String inputStatementEmployee = "UPDATE Delivery_Employee SET first_name = ?, last_name = ?, salary = ?," +
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

        String delete_statement = "DELETE FROM delivery_employees WHERE id = ?";

        PreparedStatement st = c.prepareStatement(delete_statement);

        st. setInt (1, id);

        st.executeUpdate();
    }
}