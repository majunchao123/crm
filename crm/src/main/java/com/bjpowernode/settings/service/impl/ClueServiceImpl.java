package com.bjpowernode.settings.service.impl;

import com.bjpowernode.workbench.beans.Clue;
import com.bjpowernode.workbench.mapper.ClueMapper;
import com.bjpowernode.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:ClueServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/4/7 22:58
 * @author:白白白
 */
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    ClueMapper clueMapper;

    public int saveClue(Clue clue) {
        return clueMapper.insert(clue);
    }
}