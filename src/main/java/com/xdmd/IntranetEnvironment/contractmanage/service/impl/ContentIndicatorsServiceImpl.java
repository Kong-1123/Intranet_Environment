package com.xdmd.IntranetEnvironment.contractmanage.service.impl;


import com.xdmd.IntranetEnvironment.contractmanage.mapper.ContentIndicatorsMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContentIndicatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/6
 * @description: 计划内容实现
 */
@Service
public class ContentIndicatorsServiceImpl implements ContentIndicatorsService {
    @Autowired
    ContentIndicatorsMapper contentIndicatorsMapper;
    /**
     * 新增
     * @param contentIndicatorsDTO
     * @return
     */
    @Override
    public int insert(ContentIndicatorsDTO contentIndicatorsDTO) {
        return contentIndicatorsMapper.insert(contentIndicatorsDTO);
    }

    /**
     * 根据id单查
     * @param id
     * @return
     */
    @Override
    public ContentIndicatorsDTO getIndicatorById(int id) {
        return contentIndicatorsMapper.getIndicatorById(id);
    }

    /**
     * 全部查询
     * @return
     */
    @Override
    public List<ContentIndicatorsDTO> getAllInfo() {
        return contentIndicatorsMapper.getAllInfo();
    }
}