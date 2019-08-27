package com.threeAier.app.controller;

import com.threeAier.app.dao.domain.T3aierArticle;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/article")
@Api(value = "articleController", description = "文章相关接口")
public class ArticleController extends AppBaseController{


    /**
     * 列表页
     * @return
     */
    @RequestMapping(value = "/list", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "文章列表页", notes = "")
    public ResponseEntity upload(){
//        Paginate paginate = businessLineService.queryPagenate(tCaBusinessLineVO,getPageIndex(),getPageSize());
        return new ResponseEntity(ok("获取成功",null),HttpStatus.OK);
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
        return new ResponseEntity(ok("获取成功",null), HttpStatus.OK);
    }

    /**
     * 新增
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/add", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增文章", notes = "")
    public ResponseEntity add(@ApiParam(value = "t3aierArticle", required = true) @RequestParam T3aierArticle t3aierArticle) {
        return new ResponseEntity(ok("新增成功",null), HttpStatus.OK);
    }

    /**
     * 编辑
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/modify", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "编辑文章", notes = "")
    public ResponseEntity modify(@ApiParam(value = "t3aierArticle", required = true) @RequestParam T3aierArticle t3aierArticle) {
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
        return new ResponseEntity(ok("点击量增加成功",null), HttpStatus.OK);
    }

}
