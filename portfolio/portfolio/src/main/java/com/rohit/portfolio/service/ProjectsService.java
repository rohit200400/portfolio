package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.ProjectsRepository;
import com.rohit.portfolio.entity.Project;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    /**
     * Retrieves a project by its ID.
     *
     * @param projectId The ID of the project to retrieve.
     * @return ResponseEntity containing the project if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<Project> getProject(int projectId) {
        Optional<Project> projectOptional = projectsRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return new ResponseEntity<>(projectOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves a project by UserDetail.
     *
     * @param userDetail The ID of the User.
     * @return ResponseEntity containing the project if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<List<Project>> getProjectsByUserDetail(UserDetail userDetail) {
        List<Project> projectOptional = projectsRepository.getProjectByUserDetail(userDetail);
        if (projectOptional.size() > 0) {
            return new ResponseEntity<>(projectOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a project by its ID.
     *
     * @param projectId The ID of the project to delete.
     * @return ResponseEntity with a success message if the project is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteProject(int projectId) {
        try {
            projectsRepository.deleteById(projectId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a project.
     *
     * @param project The project to save.
     * @return ResponseEntity with a success message if the project is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveProject(Project project) {
        try {
            projectsRepository.save(project);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing project.
     *
     * @param project The updated project object.
     * @return ResponseEntity with a success message if the project is updated successfully,
     *         NOT_FOUND status if the project doesn't exist, or INTERNAL_SERVER_ERROR
     *         status with an error message if an exception occurs.
     */
    public ResponseEntity<String> updateProject(Project project) {
        try {
            Optional<Project> existingProjectOptional = projectsRepository.findById(project.getProjectId());

            if (existingProjectOptional.isPresent()) {
                Project existingProject = existingProjectOptional.get();
                // Update the existing project with the new data
                existingProject.setName(project.getName());
                existingProject.setDescription(project.getDescription());
                existingProject.setTechnologiesUsed(project.getTechnologiesUsed());
                existingProject.setRelatedLink(project.getRelatedLink());

                // Save the updated project
                projectsRepository.save(existingProject);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all projects.
     *
     * @return ResponseEntity containing a list of all projects if found, or an empty list if none exist.
     */
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectsRepository.findAll();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
