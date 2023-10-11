package com.demo.service;

import com.demo.dto.ChangePasswordDTO;
import com.demo.model.UserDetails;

public interface UserService {

    public UserDetails createUser(UserDetails user);

    public boolean checkEmail(String email);

    public String changePassword(ChangePasswordDTO changePasswordDTO);
}
