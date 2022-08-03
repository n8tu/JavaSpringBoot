package com.faisal.projectmanager.services;

import com.faisal.projectmanager.models.Project;
import com.faisal.projectmanager.models.User;
import com.faisal.projectmanager.repositories.ProjectRepository;
import com.faisal.projectmanager.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> allProjects(){
        return projectRepository.findAll();
    }

    public List<Project> notRegisterdProjects(User user){
        return projectRepository.findByTeamNotContainingAndLeaderNotLike(user,user);
    }

    public List<Project> registerdProjects(User user){
        return projectRepository.findByTeamContainingOrLeaderLike(user,user);
    }

    public void createProject(Project p){
          projectRepository.save(p);
    }

    public void joinProject(User user , long project_id){
        Optional<Project> project = projectRepository.findById(project_id);
        if(project.isPresent()){
            project.get().getTeam().add(user);
            projectRepository.save(project.get());
        }
    }

    public void leaveProject(User user , long project_id){
        Optional<Project> project = projectRepository.findById(project_id);
        if(project.isPresent()){
            project.get().getTeam().remove(user);
            projectRepository.save(project.get());
        }
    }

    public Project findProject(long project_id){
        Optional<Project> project = projectRepository.findById(project_id);
        if(project.isPresent()){
            return project.get();
        }else{
            return null;
        }
    }

    public void updateProject(Project p, long project_id , BindingResult result){
        Optional<Project> project = projectRepository.findById(project_id);
        if(project.isPresent()){
            project.get().setTitle(p.getTitle());
            project.get().setDescription(p.getDescription());
            project.get().setDue_date(p.getDue_date());
            projectRepository.save(project.get());
        }
    }

    public void deleteProject(long project_id){
        Optional<Project> project = projectRepository.findById(project_id);
        if(project.isPresent()){
            projectRepository.delete(project.get());
        }
    }

    public boolean validDate(Date d){
        Optional<Date> date = Optional.ofNullable(d);
        if(date.isPresent()){
            return date.get().after(new Date());
        }else{
            return false;
        }

    }
}
