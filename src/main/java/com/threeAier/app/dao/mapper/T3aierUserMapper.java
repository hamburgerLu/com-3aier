package com.threeAier.app.dao.mapper;

import com.threeAier.app.dao.MyMapper;
import com.threeAier.app.dao.domain.T3aierUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface T3aierUserMapper extends MyMapper<T3aierUser> {

    @Select("<script>"
            + "SELECT * "
            + "FROM t_3aier_user "
            + "</script>")
    public List<T3aierUser> getList(T3aierUser t3aierUser);


    @Insert("<script>" +
            "insert into t_3aier_user (user_name,pass_word,delete_flag,status) values (#{userName} , password(#{passWord}) , 0 ,1)" +
            "</script>")
    public int insertUser(T3aierUser t3aierUser);

    @Update("<script>" +
            "update t_3aier_user set user_name = #{userName} , pass_word =  password(#{passWord}) ,  delete_flag = #{deleteFlag} , status = #{status}" +
            "where id = #{id}; " +
            "</script>")
    public int updateUser(T3aierUser t3aierUser);

}