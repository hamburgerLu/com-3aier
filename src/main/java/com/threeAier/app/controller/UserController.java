package com.threeAier.app.controller;

import com.threeAier.app.dao.domain.T3aierUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/user")
@Api(value = "userController", description = "用户相关接口")
public class UserController extends AppBaseController{



    /**
     * 列表页
     * @return
     */
    @RequestMapping(value = "/list", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "用户列表页", notes = "")
    public ResponseEntity list(){
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
    @ApiOperation(value = "根据Id查找对应的用户", notes = "")
    public ResponseEntity getById(@ApiParam(value = "用户的主键ID", required = true) @RequestParam Integer id) {
        return new ResponseEntity(ok("获取成功",null), HttpStatus.OK);
    }


    /**
     * 新增用户
     * @param t3aierUser
     * @return
     */
    @RequestMapping(value = "/add", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增管理员", notes = "")
    public ResponseEntity add(@ApiParam(value = "t3aierUser", required = true) @RequestParam T3aierUser t3aierUser){
        return new ResponseEntity(ok("新增成功",null), HttpStatus.OK);
    }


    /**
     * 修改用户
     * @param t3aierUser
     * @return
     */
    @RequestMapping(value = "/modify", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "编辑管理员", notes = "")
    public ResponseEntity modify(@ApiParam(value = "t3aierUser", required = true) @RequestParam T3aierUser t3aierUser){
        return new ResponseEntity(ok("登录成功",null), HttpStatus.OK);
    }

}
