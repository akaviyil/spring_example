package com.ults.demo.app.service;

import com.ults.demo.app.bean.Response;
import com.ults.demo.app.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository repository;

    public Response findUserDetails(String token) {
        var dbBean = repository.findUser(token);
        Response response = null;
        if(null != dbBean) {
            response = new Response();
            response.setAmount(dbBean.getAmount());
            response.setName(dbBean.getName());
            response.setEmail(dbBean.getEmail());
            response.setReferralCode(dbBean.getReferralCode());
        }
        return response;
    }
}
