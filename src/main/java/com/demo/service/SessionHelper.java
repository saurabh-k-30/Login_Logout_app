package com.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public void removeMessageFromSession()
    {
        try
        {
            HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.
                    getRequestAttributes()).getRequest().getSession();
            httpSession.removeAttribute("msg");
        }

        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
