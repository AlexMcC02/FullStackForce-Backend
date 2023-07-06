package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.SalesEmployeeService;
import org.example.cli.SalesEmployeeRequest;
import org.example.client.*;

import javax.ws.rs.*;
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

    @POST
    @Path("/salesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(SalesEmployeeRequest salesEmployee) {
        try {
            return Response.status(Response.Status.CREATED).entity(salesEmployeeService.createSalesEmployee(salesEmployee)).build();
        } catch (FailedToCreateSalesEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/salesEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalesEmployee(@PathParam("id") int id, SalesEmployeeRequest salesEmp){
        try{
            salesEmployeeService.updateSalesEmployee(id, salesEmp);

            return Response.ok().build();
        } catch(SalesEmployeeDoesNotExistException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/salesEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id){
        try{
            salesEmployeeService.deleteSalesEmployee(id);

            return Response.ok().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (FailedToDeleteSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();

        }
    }

}
