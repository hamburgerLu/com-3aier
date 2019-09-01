package com.threeAier.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.threeAier.app.common.base.AppBaseController;
import com.threeAier.app.configuration.NeedLogin;
import org.apache.commons.beanutils.BeanUtils;

import com.threeAier.app.dao.Paginate;
import com.threeAier.app.dao.domain.T3aierArticle;
import com.threeAier.app.dao.domain.T3aierArticleFile;
import com.threeAier.app.dao.mapper.T3aierArticleFileMapper;
import com.threeAier.app.service.ArticleService;
import com.threeAier.app.service.FileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/article")
@Api(value = "articleController", description = "文章相关接口")
public class ArticleController extends AppBaseController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private FileService fileService;

    /**
     * 列表页
     * @return
     */
    @RequestMapping(value = "/list", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "文章列表页", notes = "")
    public ResponseEntity list(@ApiParam(value = "t3aierArticle", required = true) @RequestBody T3aierArticle t3aierArticle){
        Paginate paginate = articleService.queryPagenate(new T3aierArticle(),getPageIndex(),getPageSize());
        return new ResponseEntity(ok("获取成功",paginate),HttpStatus.OK);
    }


    /**
     * 通过ID单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据Id查找对应的文章", notes = "")
    public ResponseEntity getById(@ApiParam(value = "文章的主键ID", required = true) @RequestParam Integer id) {
        T3aierArticle article = articleService.getById(id);
        List<T3aierArticleFile> aierArticleFiles = fileService.getFiles(id);

        JSONObject result = new JSONObject();
        result.put("article",article);
        result.put("aierArticleFiles",aierArticleFiles);

        return new ResponseEntity(ok("获取成功",result), HttpStatus.OK);
    }

    /**
     * 新增
     *
     * @param t3aierArticle
     * @return
     */
    @RequestMapping(value = "/add", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增文章", notes = "")
    @NeedLogin
    public ResponseEntity add(HttpServletRequest httpServletRequest,@ApiParam(value = "t3aierArticle", required = true) @RequestBody T3aierArticle t3aierArticle) {

//        T3aierArticle t3aierArticle = (T3aierArticle) jsonObject.get("t3aierArticle");
//        List<MultipartFile> files =  (List<MultipartFile>) jsonObject.get("files");

        HttpSession session=httpServletRequest.getSession();
        String userName = session.getAttribute("USER")==null?"admin":session.getAttribute("USER").toString();

        t3aierArticle.setCreater(userName);
        t3aierArticle.setUpdater(userName);
        t3aierArticle.setCreateTime(new Date());
        t3aierArticle.setUpdateTime(new Date());

        int id = articleService.add(t3aierArticle,null);
        return new ResponseEntity(ok("新增成功",id), HttpStatus.OK);
    }

    /**
     * 编辑
     *
     * @param t3aierArticle
     * @return
     */
    @RequestMapping(value = "/modify", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "编辑文章", notes = "")
    @NeedLogin
    public ResponseEntity modify(HttpServletRequest httpServletRequest,@ApiParam(value = "jsonObject", required = true) @RequestBody JSONObject jsonObject) throws InvocationTargetException, IllegalAccessException {

//        List<MultipartFile> files =  (List<MultipartFile>) jsonObject.get("files");
        T3aierArticle t3aierArticle = new T3aierArticle();
        BeanUtils.populate(t3aierArticle, (Map<String, ? extends Object>) jsonObject.get("t3aierArticle"));
        List<Integer> articleIds  = (List<Integer>) jsonObject.get("articleIds");

        HttpSession session=httpServletRequest.getSession();
        String userName = session.getAttribute("USER")==null?"admin":session.getAttribute("USER").toString();

        t3aierArticle.setUpdater(userName);
        t3aierArticle.setUpdateTime(new Date());

        articleService.modify(t3aierArticle,null,articleIds);
        return new ResponseEntity(ok("编辑成功",null), HttpStatus.OK);
    }


    /**
     * 增加点击量
     * @param id
     * @return
     */
    @RequestMapping(value = "/click", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据Id查找对应的业务线", notes = "")
    public ResponseEntity click(@ApiParam(value = "文章的主键ID", required = true) @RequestParam Integer id) {
        articleService.click(id);
        return new ResponseEntity(ok("点击量增加成功",null), HttpStatus.OK);
    }

}
