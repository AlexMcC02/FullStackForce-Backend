package org.example.api;

import org.example.cli.SalesEmployee;
import org.example.client.FailedToGetSalesEmployeeException;
import org.example.client.SalesEmployeeDoesNotExistException;
import org.example.db.SalesEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private SalesEmployeeDao salesEmployeeDao = new SalesEmployeeDao();
    // TODO Validator.

    public List<SalesEmployee> getAllSalesEmployees() throws FailedToGetSalesEmployeeException {
        try {
            List <SalesEmployee> salesEmployeeList = salesEmployeeDao.getAllSalesEmployees();
            return salesEmployeeList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetSalesEmployeeException();
        }
    }

    public SalesEmployee getSalesEmployeeById(int id) throws SalesEmployeeDoesNotExistException, FailedToGetSalesEmployeeException {
        try {
            SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);
            if (salesEmployee == null) {
                throw new SalesEmployeeDoesNotExistException();
            }
            return salesEmployee;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetSalesEmployeeException();
        }
    }

}
