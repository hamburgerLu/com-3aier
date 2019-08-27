package com.threeAier.app.dao.domain;

import javax.persistence.*;

@Table(name = "t_3aier_article_file")
public class T3aierArticleFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_address")
    private String fileAddress;

    @Column(name = "delete_flag")
    private Short deleteFlag;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return article_id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * @return file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return file_address
     */
    public String getFileAddress() {
        return fileAddress;
    }

    /**
     * @param fileAddress
     */
    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    /**
     * @return delete_flag
     */
    public Short getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}