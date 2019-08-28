package com.threeAier.app.service;

import com.threeAier.app.common.ThreeAierRuntimeException;
import com.threeAier.app.common.base.AppBaseService;
import com.threeAier.app.dao.Paginate;
import com.threeAier.app.dao.domain.T3aierArticle;
import com.threeAier.app.dao.mapper.T3aierArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends AppBaseService {


    @Autowired
    private T3aierArticleMapper t3aierArticleMapper;



    public Paginate queryPagenate(T3aierArticle t3aierArticle , Integer pageNo, Integer pageSize) {
        //1、增加分页参数
        this.setPage(pageNo, pageSize);
        //2、查询出所有符合要求的数据
        List<T3aierArticle> tCaBusinessLineList = t3aierArticleMapper.getList(t3aierArticle);
        //3、组装成paginate对象
        Paginate paginate = createPaginate(pageNo, pageSize, tCaBusinessLineList);
        return paginate;


    }


    public T3aierArticle getById(Integer id){
        if(id == null){
            throw new ThreeAierRuntimeException("主键缺失");
        }
        return t3aierArticleMapper.selectByPrimaryKey(id);
    }



    public void add(T3aierArticle t3aierArticle){
        if(t3aierArticle.getName()==null){
            throw new ThreeAierRuntimeException("文章标题缺失");
        }

        int result = t3aierArticleMapper.insertSelective(t3aierArticle);
        if(result<=0){
            throw new ThreeAierRuntimeException("插入失败");
        }

    }


    public void modify(T3aierArticle t3aierArticle){
        if(t3aierArticle.getId()==null){
            throw new ThreeAierRuntimeException("文章主键缺失");
        }
        if(t3aierArticle.getName()==null){
            throw new ThreeAierRuntimeException("文章标题缺失");
        }

        int result = t3aierArticleMapper.updateByPrimaryKey(t3aierArticle);
        if(result<=0){
            throw new ThreeAierRuntimeException("更新失败");
        }
    }


    /**
     * 增加点击量
     * @param id
     */
    public void click(Integer id){
        t3aierArticleMapper.click(id);
    }
}
