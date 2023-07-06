package org.example.db;

import org.example.cli.DeliveryEmployee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException
    {
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

}
