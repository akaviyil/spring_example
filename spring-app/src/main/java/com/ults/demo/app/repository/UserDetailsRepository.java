package com.ults.demo.app.repository;

import com.ults.demo.app.bean.DataStoreBean;
import com.ults.demo.app.bean.LoginRequest;
import com.ults.demo.app.bean.RegistrationRequest;
import com.ults.demo.app.datastore.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDetailsRepository {

    @Autowired
    private DataStore dataStore;

    private static final long TOKEN_VALIDITY = 10;

    public DataStoreBean performLogin(LoginRequest loginRequest) {
        ConcurrentHashMap<String, DataStoreBean> dataBase = dataStore.getDataStore();
        String token = null;
        DataStoreBean dbBean = null;
        for (Map.Entry<String, DataStoreBean> entry : dataBase.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(loginRequest.getName()) && entry.getValue().getPassword().equals(loginRequest.getPassword())) {
                if (entry.getValue().getToken() == null || entry.getValue().getTokenExpiry().isBefore(LocalDateTime.now())) {
                    token = UUID.randomUUID().toString();
                    entry.getValue().setToken(token);
                }
                entry.getValue().setTokenExpiry(LocalDateTime.now().plusMinutes(TOKEN_VALIDITY));
                dbBean = entry.getValue();
                break;
            }
        }
        return dbBean;
    }

    public DataStoreBean performRegistration(RegistrationRequest registrationRequest) {
        ConcurrentHashMap<String, DataStoreBean> dataBase = dataStore.getDataStore();
        var dbBean = new DataStoreBean();
        dbBean.setName(registrationRequest.getName());
        dbBean.setPassword(registrationRequest.getPassword());
        dbBean.setEmail(registrationRequest.getEmail());
        dbBean.setReferralCode(UUID.randomUUID().toString().substring(0, 4).toUpperCase());
        var referee = validateReferralCode(registrationRequest.getReferralCode());
        if(referee != null) {
            referee.setAmount(referee.getAmount() + 100);
            dbBean.setAmount(50.0);
        } else {
            dbBean.setAmount(0.0);
        }
        dataBase.put(registrationRequest.getName(), dbBean);
        return  dbBean;
    }

    public DataStoreBean findUser(String token) {
        ConcurrentHashMap<String, DataStoreBean> dataBase = dataStore.getDataStore();
        DataStoreBean response = null;
        for (Map.Entry<String, DataStoreBean> entry : dataBase.entrySet()) {
            if (entry.getValue().getToken().equals(token)) {
                if (entry.getValue().getTokenExpiry().isAfter(LocalDateTime.now())) {
                    response = entry.getValue();
                }
            }
        }
        return response;
    }

    private DataStoreBean validateReferralCode(String referralCode) {
        ConcurrentHashMap<String, DataStoreBean> dataBase = dataStore.getDataStore();
        DataStoreBean response = null;
        for (Map.Entry<String, DataStoreBean> entry : dataBase.entrySet()) {
            if(entry.getValue().getReferralCode().equals(referralCode)) {
                response = entry.getValue();
                break;
            }
        }
        return response;
    }

    public Boolean findUserByName(String userName) {
        ConcurrentHashMap<String, DataStoreBean> dataBase = dataStore.getDataStore();
        Boolean userExists = Boolean.FALSE;
        for (Map.Entry<String, DataStoreBean> entry : dataBase.entrySet()) {
            if(userName.equals(entry.getKey())){
                userExists = Boolean.TRUE;
                break;
            }
        }
        return userExists;
    }
}
