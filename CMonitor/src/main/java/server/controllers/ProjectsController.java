package server.controllers;

import models.Project;
import org.springframework.web.bind.annotation.*;
import server.services.ProjectService;

import java.util.concurrent.ExecutionException;

@RestController
public class ProjectsController {

    private final ProjectService projectService;

    public ProjectsController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/post-project")
    public String postController(@RequestBody Project project) throws ExecutionException, InterruptedException {
        return projectService.createProject(project);
    }

    @GetMapping("/get-project")
    public Project getController(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return projectService.getProject(documentId);
    }
}
