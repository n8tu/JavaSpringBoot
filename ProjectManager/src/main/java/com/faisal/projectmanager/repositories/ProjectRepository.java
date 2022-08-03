package com.faisal.projectmanager.repositories;

import com.faisal.projectmanager.models.Project;
import com.faisal.projectmanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    List<Project> findAll();
    List<Project> findByTeamNotContainingAndLeaderNotLike(User user,User user2);

    List<Project> findByTeamContainingOrLeaderLike(User user,User user2);
}
