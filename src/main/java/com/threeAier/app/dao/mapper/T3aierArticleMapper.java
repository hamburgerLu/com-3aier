package com.threeAier.app.dao.mapper;

import com.threeAier.app.dao.MyMapper;
import com.threeAier.app.dao.domain.T3aierArticle;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface T3aierArticleMapper extends MyMapper<T3aierArticle> {
    @Select("<script>"
            + "SELECT * "
            + "FROM t_3aier_article where delete_flag = 0"
            + "</script>")
    public List<T3aierArticle> getList(T3aierArticle t3aierArticle);


    @Update("<script>"
            + "UPDATE t_3aier_article set page_view = page_view+1 where id = #{id}; "
            + "</script>")
    public int click(Integer id);
}