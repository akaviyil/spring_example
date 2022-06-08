package com.ults.demo.app.datastore;

import com.ults.demo.app.bean.DataStoreBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataStore {
    private ConcurrentHashMap<String, DataStoreBean> dataStore;

    @PostConstruct
    public void init() {
        dataStore = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, DataStoreBean> getDataStore() {
        return dataStore;
    }
}
