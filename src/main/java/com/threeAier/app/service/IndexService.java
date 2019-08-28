package com.threeAier.app.service;

import com.threeAier.app.dao.domain.T3aierUser;
import com.threeAier.app.dao.mapper.T3aierUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {


    @Autowired
    private T3aierUserMapper t3aierUserMapper;

    public Boolean login(String userName,String passWord){

        T3aierUser user = t3aierUserMapper.login(userName,passWord);
        if(user!=null){


            return true;
        }
        return false;
    }






}
