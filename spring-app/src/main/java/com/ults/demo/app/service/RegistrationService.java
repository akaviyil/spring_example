package com.ults.demo.app.service;

import com.ults.demo.app.bean.RegistrationRequest;
import com.ults.demo.app.bean.Response;
import com.ults.demo.app.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserDetailsRepository repository;

    public Response registerUser(RegistrationRequest registrationRequest) {
        var response= new Response();
        var dbBean = repository.performRegistration(registrationRequest);
        response.setReferralCode(dbBean.getReferralCode());
        response.setAmount(dbBean.getAmount());
        response.setName(dbBean.getName());
        return response;
    }

    public Boolean checkUserExists(String userName) {
        return repository.findUserByName(userName);
    }

}
