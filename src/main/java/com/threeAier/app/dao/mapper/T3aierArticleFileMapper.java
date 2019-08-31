package com.threeAier.app.dao.mapper;

import com.threeAier.app.dao.MyMapper;
import com.threeAier.app.dao.domain.T3aierArticleFile;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface T3aierArticleFileMapper extends MyMapper<T3aierArticleFile> {

    @Update("<script>" +
            "update t_3aier_article_file set delete_flag = 1 where article_id = #{id} ; " +
            "</script>")
    public void deleteByArticleId(int id);



    @Update("<script>" +
            "update t_3aier_article_file set delete_flag = 1 where id in " +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            " #{item}" +
            "</foreach> " +
            "</script>")
    public void deleteByArticleFileIds(List<Integer> list);





    @Select("<script>" +
            "select * from t_3aier_article_file where  delete_flag = 0 and  article_id = #{id} ;  " +
            "</script>")
    public List<T3aierArticleFile> selectByArticleId(int id);
}