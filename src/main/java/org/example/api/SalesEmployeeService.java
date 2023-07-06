package org.example.api;

import org.example.cli.SalesEmployee;
import org.example.cli.SalesEmployeeRequest;
import org.example.client.*;
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

    public int createSalesEmployee(SalesEmployeeRequest salesEmployee) throws FailedToCreateSalesEmployeeException {
        try {
            int id = salesEmployeeDao.createSalesEmployee(salesEmployee);
            if (id == -1) {
                throw new FailedToCreateSalesEmployeeException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateSalesEmployeeException();
        }
    }

    public void updateSalesEmployee(int id, SalesEmployeeRequest salesEmployee) throws SalesEmployeeDoesNotExistException, FailedToUpdateSalesEmployeeException {
        try{
//            String validation = productValidator.isValidProduct(product);

            /*if(validation != null){
                throw new InvalidProductException(validation);
            }*/

            SalesEmployee salesEmployeeToUpdate = salesEmployeeDao.getSalesEmployeeById(id);

            if(salesEmployeeToUpdate == null){
                throw new SalesEmployeeDoesNotExistException();
            }

            salesEmployeeDao.updateSalesEmployee(id, salesEmployee);
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateSalesEmployeeException();
        }
    }


    public void deleteSalesEmployee(int id) throws SalesEmployeeDoesNotExistException, FailedToDeleteSalesEmployeeException {
        try{
            SalesEmployee salesEmployeeToDelete = salesEmployeeDao.getSalesEmployeeById(id);

            if(salesEmployeeToDelete == null){
                throw new SalesEmployeeDoesNotExistException();
            }


            salesEmployeeDao.deleteSalesEmployee(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteSalesEmployeeException();

        }
    }

}
