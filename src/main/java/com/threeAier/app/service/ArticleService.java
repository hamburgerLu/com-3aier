package com.threeAier.app.service;

import com.threeAier.app.common.ThreeAierRuntimeException;
import com.threeAier.app.common.base.AppBaseService;
import com.threeAier.app.dao.Paginate;
import com.threeAier.app.dao.domain.T3aierArticle;
import com.threeAier.app.dao.mapper.T3aierArticleFileMapper;
import com.threeAier.app.dao.mapper.T3aierArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ArticleService extends AppBaseService {


    @Autowired
    private T3aierArticleMapper t3aierArticleMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private T3aierArticleFileMapper t3aierArticleFileMapper;

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



    public int add(T3aierArticle t3aierArticle,List<MultipartFile> files){
        if(t3aierArticle.getName()==null){
            throw new ThreeAierRuntimeException("文章标题缺失");
        }

        t3aierArticle.setDeleteFlag((short)0);
        t3aierArticle.setStatus((short)1);
        if(t3aierArticle.getPageView()==null || t3aierArticle.getPageView()==0){
            t3aierArticle.setPageView(1);
        }

        int result = t3aierArticleMapper.insertSelective(t3aierArticle);
        if(result<=0){
            throw new ThreeAierRuntimeException("插入失败");
        }

        if(files!=null){
            //插入附件
            for(MultipartFile file : files){
                fileService.upload(file,t3aierArticle.getId());
            }
        }

        return t3aierArticle.getId();
    }


    public void modify(T3aierArticle t3aierArticle,List<MultipartFile> files,List<Integer> articleIds){
        if(t3aierArticle.getId()==null){
            throw new ThreeAierRuntimeException("文章主键缺失");
        }

//        if(t3aierArticle.getName()==null){
//            throw new ThreeAierRuntimeException("文章标题缺失");
//        }

        int result = t3aierArticleMapper.updateByPrimaryKeySelective(t3aierArticle);
        if(result<=0){
            throw new ThreeAierRuntimeException("更新失败");
        }

        if(articleIds!=null && articleIds.size()>0){
            //删除所有附件
            t3aierArticleFileMapper.deleteByArticleFileIds(articleIds);
        }


        if(files!=null) {
            //插入附件
            for (MultipartFile file : files) {
                fileService.upload(file, t3aierArticle.getId());
            }
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
