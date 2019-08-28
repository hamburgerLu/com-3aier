package com.threeAier.app.service;

import com.threeAier.app.common.ThreeAierRuntimeException;
import com.threeAier.app.common.base.AppBaseService;
import com.threeAier.app.dao.Paginate;
import com.threeAier.app.dao.domain.T3aierUser;
import com.threeAier.app.dao.mapper.T3aierUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AppBaseService {


    @Autowired
    private T3aierUserMapper t3aierUserMapper;



    public Paginate queryPagenate(T3aierUser t3aierUser , Integer pageNo, Integer pageSize) {
        //1、增加分页参数
        this.setPage(pageNo, pageSize);
        //2、查询出所有符合要求的数据
        List<T3aierUser> tCaBusinessLineList = t3aierUserMapper.getList(t3aierUser);
        //3、组装成paginate对象
        Paginate paginate = createPaginate(pageNo, pageSize, tCaBusinessLineList);
        return paginate;


    }


    public T3aierUser getById(Integer id){
        if(id == null){
            throw new ThreeAierRuntimeException("主键缺失");
        }
        return t3aierUserMapper.selectByPrimaryKey(id);
    }



    public void add(T3aierUser t3aierUser){
        if(t3aierUser.getUserName()==null){
            throw new ThreeAierRuntimeException("人员账号缺失");
        }
        if(t3aierUser.getPassWord()==null){
            throw new ThreeAierRuntimeException("人员密码缺失");
        }

        int result = t3aierUserMapper.insertUser(t3aierUser);
        if(result<=0){
            throw new ThreeAierRuntimeException("插入失败");
        }

    }


    public void modify(T3aierUser t3aierUser){
        if(t3aierUser.getId()==null){
            throw new ThreeAierRuntimeException("人员主键缺失");
        }
        if(t3aierUser.getUserName()==null){
            throw new ThreeAierRuntimeException("人员账号缺失");
        }
        if(t3aierUser.getPassWord()==null){
            throw new ThreeAierRuntimeException("人员密码缺失");
        }

        int result = t3aierUserMapper.updateUser(t3aierUser);
        if(result<=0){
            throw new ThreeAierRuntimeException("更新失败");
        }
    }


}
