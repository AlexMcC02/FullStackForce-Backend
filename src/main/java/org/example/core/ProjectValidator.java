package org.example.core;

import org.example.cli.ProjectRequest;

public class ProjectValidator {
    public String isValidProject(ProjectRequest project) {
        if(project.getName().length() > 50) {
            return "Project name must not be more than 50 characters";
        }
        return null;
    }
}
