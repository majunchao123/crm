package com.bjpowernode.workbench.service;

import com.bjpowernode.workbench.beans.Clue;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueService
 * Package:com.bjpowernode.workbench.service
 * Description: 描述信息
 *
 * @date:2022/4/7 22:57
 * @author:白白白
 */
public interface ClueService {
    int saveClue(Clue clue);
    List<Clue> queryClueListByPage(Map map);

    int queryCountClueListByPage(Map map);

}
