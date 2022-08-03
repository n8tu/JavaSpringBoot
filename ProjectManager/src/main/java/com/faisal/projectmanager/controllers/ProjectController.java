package com.faisal.projectmanager.controllers;

import com.faisal.projectmanager.models.Project;
import com.faisal.projectmanager.models.User;
import com.faisal.projectmanager.services.ProjectService;
import com.faisal.projectmanager.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(
            Model model,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }
        User user = userService.findUser(
                (long) session.getAttribute("user_id")
        );
        model.addAttribute("available_projects",projectService.notRegisterdProjects(user));
        model.addAttribute("registerd_projects",projectService.registerdProjects(user));
        model.addAttribute("user",user);
        return "dashboard.jsp";
    }

    @RequestMapping(value = "/projects/new",method = RequestMethod.GET)
    public String newProject(
            @ModelAttribute("project") Project project,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }
        return "new_project.jsp";
    }

    @RequestMapping(value = "/projects/new",method = RequestMethod.POST)
    public String createProject(
            @Valid @ModelAttribute("project") Project project,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }
        if(!projectService.validDate(project.getDue_date())){
            result.addError(new FieldError("Date","Date","Date cannot be in the past"));
        }

        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/projects/new";
        }
        User user = userService.findUser((long) session.getAttribute("user_id"));
        project.setLeader(user);
        projectService.createProject(project);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/projects/{project_id}/join" , method = RequestMethod.GET)
    public String joinProject(
            @PathVariable(value = "project_id") long project_id,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }

        User user = userService.findUser(
                (long) session.getAttribute("user_id")
        );
        projectService.joinProject(user,project_id);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/projects/{project_id}/leave" , method = RequestMethod.GET)
    public String leaveProject(
            @PathVariable(value = "project_id") long project_id,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }
        User user = userService.findUser(
                (long) session.getAttribute("user_id")
        );
        projectService.leaveProject(user,project_id);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/projects/{project_id}/edit" , method = RequestMethod.GET)
    public String editProject(
            @PathVariable("project_id") long project_id,
            @ModelAttribute("project_data") Project project,
            Model model,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }
        if(!isOwner(session,projectService.findProject(project_id))){
            return "redirect:/dashboard";
        }
        model.addAttribute("project", projectService.findProject(project_id));
        return "edit_project.jsp";

    }

    @RequestMapping(value = "/projects/{project_id}/edit" , method = RequestMethod.POST)
    public String updateProject(
            @PathVariable("project_id") long project_id,
            @Valid @ModelAttribute("project_data") Project project,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }

        if(!isOwner(session,projectService.findProject(project_id))){
            return "redirect:/dashboard";
        }

        if(!projectService.validDate(project.getDue_date())){
            result.addError(new FieldError("Date","Date","Date cannot be in the past"));
        }
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/projects/"+ project_id +"/edit";
        }
        projectService.updateProject(project,project_id,result);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/projects/{project_id}",method = RequestMethod.GET)
    public String showProject(
            @PathVariable(value = "project_id") long project_id,
            Model model,
            HttpSession session
    ){
        model.addAttribute("project",projectService.findProject(project_id));
        model.addAttribute("user",userService.findUser(
                (long) session.getAttribute("user_id")
        ));
        return "show_project.jsp";
    }

    @RequestMapping(value = "/projects/{project_id}/delete",method = RequestMethod.DELETE)
    public String deleteProject(
            @PathVariable(value = "project_id") long project_id,
            HttpSession session
    ){
        if(!isLogin(session)){
            return "redirect:/login";
        }

        if(!isOwner(session,projectService.findProject(project_id))){
            return "redirect:/dashboard";
        }

        projectService.deleteProject(project_id);
        return "redirect:/dashboard";
    }





    /*------ Helpers ------*/
    public boolean isLogin(
            HttpSession session
    ){
        if(session.getAttribute("user_id") == null){
            return false;
        }else{
            return true;
        }
    }

    public boolean isOwner(HttpSession session,Project project){
        Object user_id = session.getAttribute("user_id");
        if(user_id == null){
            return false;
        }else{
            return project.getLeader().getId() == (long) user_id ;
        }
    }



}
