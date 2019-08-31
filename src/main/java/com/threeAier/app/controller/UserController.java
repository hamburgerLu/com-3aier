package com.threeAier.app.controller;

import com.threeAier.app.common.base.AppBaseController;
import com.threeAier.app.configuration.NeedLogin;
import com.threeAier.app.dao.Paginate;
import com.threeAier.app.dao.domain.T3aierUser;
import com.threeAier.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/user")
@Api(value = "userController", description = "用户相关接口")
public class UserController extends AppBaseController {


    @Autowired
    private UserService userService;

    /**
     * 列表页
     * @return
     */
    @RequestMapping(value = "/list", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "用户列表页", notes = "")
    @NeedLogin
    public ResponseEntity list(@ApiParam(value = "t3aierUser", required = true) @RequestBody T3aierUser t3aierUser){
        Paginate paginate = userService.queryPagenate(t3aierUser,getPageIndex(),getPageSize());
        return new ResponseEntity(ok("获取成功",paginate),HttpStatus.OK);
    }


    /**
     * 通过ID单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "根据Id查找对应的用户", notes = "")
    @NeedLogin
    public ResponseEntity getById(@ApiParam(value = "用户的主键ID", required = true) @RequestParam Integer id) {
        T3aierUser t3aierUser = userService.getById(id);
        return new ResponseEntity(ok("获取成功",t3aierUser), HttpStatus.OK);
    }


    /**
     * 新增用户
     * @param t3aierUser
     * @return
     */
    @RequestMapping(value = "/add", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "新增管理员", notes = "")
    @NeedLogin
    public ResponseEntity add(@ApiParam(value = "t3aierUser", required = true) @RequestBody T3aierUser t3aierUser){
        userService.add(t3aierUser);
        return new ResponseEntity(ok("新增成功",null), HttpStatus.OK);
    }


    /**
     * 修改用户
     * @param t3aierUser
     * @return
     */
    @RequestMapping(value = "/modify", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "编辑管理员", notes = "")
    @NeedLogin
    public ResponseEntity modify(@ApiParam(value = "t3aierUser", required = true) @RequestBody T3aierUser t3aierUser){
        userService.modify(t3aierUser);
        return new ResponseEntity(ok("登录成功",null), HttpStatus.OK);
    }

}
