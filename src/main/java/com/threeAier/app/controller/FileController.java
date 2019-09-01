package com.threeAier.app.controller;

import com.threeAier.app.common.base.AppBaseController;
import com.threeAier.app.configuration.NeedLogin;
import com.threeAier.app.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/file")
@Api(value = "fileController", description = "文件相关上传下载")
public class FileController extends AppBaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "上传接口", notes = "")
    @ResponseBody
    @NeedLogin
    public ResponseEntity upload( MultipartFile file, Integer id){
        fileService.upload(file,id);
        return new ResponseEntity(ok("上传成功",null), HttpStatus.OK);
    }

    @RequestMapping(value = "/download", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "下载接口", notes = "")
    public ResponseEntity download(int id){
        fileService.download(id);
        return new ResponseEntity(ok("下载成功",null), HttpStatus.OK);
    }


}
