package com.demo.service;

import com.demo.dto.ChangePasswordDTO;
import com.demo.model.UserDetails;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepo;
    @Override
    public UserDetails createUser(UserDetails user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        UserDetails userDetails = userRepo.findFirstByEmail(changePasswordDTO.getEmail());
        if(userDetails != null){
            if(changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
                userDetails.setPassword(changePasswordDTO.getNewPassword());
                userRepo.save(userDetails);
                return "PasswordChanged";
            } else{
                return "PasswordDoNotMatch";
            }
        } else{
            return "EmailNotRegistered";
        }
    }
}
