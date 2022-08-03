package com.faisal.projectmanager.services;

import com.faisal.projectmanager.models.Login;
import com.faisal.projectmanager.models.User;
import com.faisal.projectmanager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(
            User user,
            BindingResult result
    ){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            result.addError(new FieldError("email","email","The email is already registered, Try login"));
        }
        if(!user.getPassword().equals(user.getConfirm_password())){
            result.addError(new FieldError("password","password","password and confirm password must be matched!"));
        }
        if(!result.hasErrors()){
            String hashed_password = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
            user.setPassword(hashed_password);
            return userRepository.save(user);
        }
        return null;
    }

    public User login(
            Login login,
            BindingResult result
    ){
        Optional<User> user = userRepository.findByEmail(login.getEmail());
        if(!user.isPresent()){
            result.addError(new FieldError("login","login","Email or password is incorrect"));
        }else{
            if(!BCrypt.checkpw(login.getPassword() , user.get().getPassword())){
                result.addError(new FieldError("login","login","Email or password is incorrect"));
            }else{
                if(!result.hasErrors()){
                    return user.get();
                }
            }
        }
        return null;
    }

    public User findUser(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
}
