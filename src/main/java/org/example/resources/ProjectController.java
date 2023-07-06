package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.ProjectService;
import org.example.cli.ProjectRequest;
import org.example.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Project")
@Path("/api")

public class ProjectController {

    private ProjectService projectService = new ProjectService();

    @GET
    @Path("/project")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject() {
        try {
            return Response.ok(projectService.getAllProjects()).build();
        } catch (FailedToGetProjectException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectById(@PathParam("id") int id) {
        try {
            return Response.ok(projectService.getProjectByID(id)).build();
        } catch (FailedToGetProjectException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (ProjectDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @POST
    @Path("/project")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectRequest project) {
        try {
            return Response.ok(projectService.createProject(project)).build();
        } catch (FailedToCreateProjectException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (InvalidProjectException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectById(@PathParam("id") int id, ProjectRequest project) {
        try {
            projectService.updateProject(project, id);
            return Response.ok().build();
        } catch (ProjectDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (InvalidProjectException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (FailedToUpdateProjectException e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProjectById(@PathParam("id") int id) {
        try {
            projectService.deleteProject(id);
            return Response.ok().build();
        } catch (ProjectDoesNotExistException e){
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        } catch (FailedToDeleteProjectException e) {
            throw new RuntimeException(e);
        }
    }
}