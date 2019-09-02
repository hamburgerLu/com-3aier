package com.threeAier.app.service;

import com.threeAier.app.common.base.AppBaseService;
import com.threeAier.app.dao.domain.T3aierArticleFile;
import com.threeAier.app.dao.mapper.T3aierArticleFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
@Slf4j
public class FileService extends AppBaseService {


    @Autowired
    private T3aierArticleFileMapper t3aierArticleFileMapper;

    private static String address_pre = "/root/upload/";



    public List<T3aierArticleFile> getFiles(int id){
       return t3aierArticleFileMapper.selectByArticleId(id);
    }


    /**
     * 上传文件
     *
     * @param multipartFile
     */
    public void upload(MultipartFile multipartFile, Integer articleId) {
        String fileName = multipartFile.getOriginalFilename();
        String fileAddress = address_pre + fileName;

        try (FileInputStream fis = (FileInputStream) multipartFile.getInputStream();
             FileOutputStream fos = new FileOutputStream(new File(fileAddress))) {

            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                fos.write(b, 0, n);
            }

            //入库
            T3aierArticleFile articleFile = new T3aierArticleFile();
            articleFile.setArticleId(articleId);
            articleFile.setDeleteFlag((short) 0);
            articleFile.setFileName(fileName);
            articleFile.setFileAddress(fileAddress);
            t3aierArticleFileMapper.insertSelective(articleFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载
     *
     * @param id
     */
    public void download(Integer id) {
        T3aierArticleFile articleFile = t3aierArticleFileMapper.selectByPrimaryKey(id);
        String address = articleFile.getFileAddress();

        //导出到页面
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try (InputStream in = new FileInputStream(address);) {
            setResponseHeader(response, articleFile.getFileAddress());
            OutputStream os = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            in.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导出到页面出错");
        }


    }


    /**
     * 发送响应流
     *
     * @param response
     * @param fileName
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                log.error("获取文件名失败");
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            log.error("设置头失败");
        }
    }
}