package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.SalesEmployeeService;
import org.example.client.FailedToGetSalesEmployeeException;
import org.example.client.SalesEmployeeDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("SalesEmployees")
@Path("/api")
public class SalesEmployeeController {

    private SalesEmployeeService salesEmployeeService = new SalesEmployeeService();

    @GET
    @Path("/salesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployees() {
        try {
            return Response.ok(salesEmployeeService.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/salesEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("id") int id) {
        try {
            return Response.ok(salesEmployeeService.getSalesEmployeeById(id)).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (SalesEmployeeDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }
}
