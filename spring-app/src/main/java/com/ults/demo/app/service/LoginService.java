package com.ults.demo.app.service;

import com.ults.demo.app.bean.LoginRequest;
import com.ults.demo.app.bean.Response;
import com.ults.demo.app.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDetailsRepository repository;

    public Response validateUser(final LoginRequest loginRequest) {
        Response response = null;
        var dbBean = repository.performLogin(loginRequest);
        if(null != dbBean) {
            response = new Response();
            response.setName(dbBean.getName());
            response.setAmount(dbBean.getAmount());
            response.setReferralCode(dbBean.getReferralCode());
            response.setToken(dbBean.getToken());
        }
        return response;
    }
}
