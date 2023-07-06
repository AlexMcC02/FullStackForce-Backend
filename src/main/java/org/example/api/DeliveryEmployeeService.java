package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.client.DeliveryEmployeeDoesNotExistException;
import org.example.client.FailedToGetDeliveryEmployeeException;
import org.example.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws FailedToGetDeliveryEmployeeException {
        try {
            List<DeliveryEmployee> deliveryEmployeeList = deliveryEmployeeDao.getAllDeliveryEmployees();

            return deliveryEmployeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetDeliveryEmployeeException();
        }
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetDeliveryEmployeeException, DeliveryEmployeeDoesNotExistException {
        try {
            DeliveryEmployee delivery_employee = deliveryEmployeeDao.getDeliveryEmployeeById(id);

            if (delivery_employee == null) {
                throw new DeliveryEmployeeDoesNotExistException();
            }
            return delivery_employee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetDeliveryEmployeeException();
        }
    }
}
