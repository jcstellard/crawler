package com.nb.pachong.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailDao {

    /**
     * getEmails
     */
    List<String> getEmails();
}
