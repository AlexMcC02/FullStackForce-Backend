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

        String insertStatement = "INSERT INTO delivery_employees (name, salary, national_insurance_number, bank_account_number) VALUES (?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, delivery_employee.getName());
        st.setDouble(2, delivery_employee.getSalary());
        st.setString(3, delivery_employee.getNational_insurance_number());
        st.setString(4, delivery_employee.getBank_account_number());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE delivery_employees SET name = ?, salary = ?, national_insurance_number = ?, bank_account_number = ? WHERE id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, delivery_employee.getName());
        st.setDouble(2, delivery_employee.getSalary());
        st.setString(3, delivery_employee.getNational_insurance_number());
        st.setString(4, delivery_employee.getBank_account_number());
        st.setInt(5, id);

        st.executeUpdate();
    }
    public void deleteDeliveryEmployee (int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String delete_statement = "DELETE FROM delivery_employees WHERE id = ?";

        PreparedStatement st = c.prepareStatement(delete_statement);

        st. setInt (1, id);

        st.executeUpdate();
    }
}