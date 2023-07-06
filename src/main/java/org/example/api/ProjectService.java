package org.example.api;

import org.example.cli.Project;
import org.example.cli.ProjectRequest;
import org.example.client.*;
import org.example.core.ProjectValidator;
import org.example.db.ProjectDao;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    ProjectDao projectDao = new ProjectDao();
    ProjectValidator projectValidator = new ProjectValidator();
    public List<Project> getAllProjects() throws FailedToGetProjectException {
        try{
            List<Project> projectList = projectDao.getAllProjects();
            return projectList;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProjectException();
        }
    }

    public Project getProjectByID(int id) throws ProjectDoesNotExistException, FailedToGetProjectException {
        try {
            Project project = projectDao.getProjectByID(id);
            if(project == null){
                throw new ProjectDoesNotExistException();
            }
            return project;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProjectException();
        }
    }

    public int createProject(ProjectRequest project) throws FailedToCreateProjectException, InvalidProjectException {
        try {
            String validation = projectValidator.isValidProject(project);
            if(validation != null){
                throw new InvalidProjectException(validation);
            }
            int id = projectDao.createProject(project);

            if(id == -1){
                throw new FailedToCreateProjectException();
            }

            return id;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProjectException();
        }
    }

    public void updateProject(ProjectRequest project, int id) throws FailedToUpdateProjectException, ProjectDoesNotExistException, InvalidProjectException {
        try{
            String validation = projectValidator.isValidProject(project);
            if(validation != null){
                throw new InvalidProjectException(validation);
            }

            Project projectToUpdate = projectDao.getProjectByID(id);

            if(projectToUpdate == null){
                throw new ProjectDoesNotExistException();
            }

            projectDao.updateProject(id, project);
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProjectException();
        }
    }

    public void deleteProject(int id) throws ProjectDoesNotExistException, FailedToDeleteProjectException {
        try{
            Project projectToDelete = projectDao.getProjectByID(id);

            if(projectToDelete == null){
                throw new ProjectDoesNotExistException();
            }

            projectDao.deleteProject(id);
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteProjectException();
        }
    }
}
