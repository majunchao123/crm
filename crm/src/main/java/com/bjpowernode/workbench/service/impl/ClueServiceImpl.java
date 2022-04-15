package com.bjpowernode.workbench.service.impl;

import com.bjpowernode.commons.constants.Constant;
import com.bjpowernode.commons.utils.DateUtils;
import com.bjpowernode.commons.utils.UUIDUtils;
import com.bjpowernode.settings.beans.User;
import com.bjpowernode.workbench.beans.*;
import com.bjpowernode.workbench.mapper.*;
import com.bjpowernode.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    ContactsMapper contactsMapper;
    @Autowired
    CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    ClueRemarkMapper clueRemarkMapper;
    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    TranMapper tranMapper;
    @Autowired
    TranRemarkMapper tranRemarkMapper;




    public int saveClue(Clue clue) {
        return clueMapper.insert(clue);
    }

    public List<Clue> queryClueListByPage(Map map) {
        return clueMapper.selectClueListByPage(map);
    }

    public int queryCountClueListByPage(Map map) {
        return clueMapper.selectCountClueListByPage(map);
    }

    public Clue queryClueDetailById(String id) {
        return clueMapper.selectClueDetailById(id);
    }

    public void saveConvertClue(Map map) {
        User user = (User) map.get(Constant.SESSION_USER);
        String isCreateTran=(String) map.get("isCreateTran");
        String id = (String) map.get("clueId");

        //根据id查询clue
        Clue clue = clueMapper.selectByPrimaryKey(id);

        //把clue线索内容提取到客户表中
        Customer c = new Customer();
        c.setAddress(clue.getAddress());
        c.setContactSummary(clue.getContactSummary());
        c.setCreateBy(user.getId());
        c.setCreateTime(DateUtils.formatDateAndTime(new Date()));
        c.setDescription(clue.getDescription());
        c.setId(UUIDUtils.getUUID());
        c.setName(clue.getCompany());
        c.setNextContactTime(clue.getNextContactTime());
        c.setOwner(user.getId());
        c.setPhone(clue.getPhone());
        c.setWebsite(clue.getWebsite());
        customerMapper.insert(c);


        //把clue线索提取到联系人表中
        Contacts co = new Contacts();
        co.setAddress(clue.getAddress());
        co.setAppellation(clue.getAppellation());
        co.setContactSummary(clue.getContactSummary());
        co.setCreateBy(user.getId());
        co.setCreateTime(DateUtils.formatDateAndTime(new Date()));
        co.setCustomerId(c.getId());
        co.setDescription(clue.getDescription());
        co.setEmail(clue.getEmail());
        co.setFullname(clue.getFullname());

        co.setId(UUIDUtils.getUUID());
        co.setJob(clue.getJob());
        co.setMphone(clue.getMphone());
        co.setNextContactTime(clue.getNextContactTime());
        co.setOwner(user.getId());
        co.setSource(clue.getSource());
        contactsMapper.insert(co);



        //把clue备注转移到客户表中
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkListByClueId(id);
        if (clueRemarkList != null && clueRemarkList.size() > 0) {
            CustomerRemark customerRemark = null;
            ContactsRemark contactsRemark = null;

            for (ClueRemark crl :
                    clueRemarkList) {
                customerRemark = new CustomerRemark();
                contactsRemark = new ContactsRemark();
                customerRemark.setCreateBy(crl.getCreateBy());
                customerRemark.setCreateTime(crl.getCreateTime());
                customerRemark.setCustomerId(c.getId());
                customerRemark.setEditBy(crl.getEditBy());
                customerRemark.setEditFlag(crl.getEditFlag());
                customerRemark.setEditTime(crl.getEditTime());
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemark.setNoteContent(crl.getNoteContent());
                contactsRemark.setContactsId(co.getId());
                contactsRemark.setCreateBy(crl.getCreateBy());
                contactsRemark.setCreateTime(crl.getCreateTime());
                contactsRemark.setEditBy(crl.getEditBy());
                contactsRemark.setEditFlag(crl.getEditFlag());
                contactsRemark.setEditTime(crl.getEditTime());
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setNoteContent(crl.getNoteContent());
                //将客户线索 联系人线索加入到 表中
                customerRemarkMapper.insert(customerRemark);
                contactsRemarkMapper.insert(contactsRemark);
            }
        }



        //把线索和市场活动关系表 转为 联系人和市场活动表
        List<ClueActivityRelation> clueActRelaList = clueActivityRelationMapper.selectClueActRelationListByClueId(id);
        if (clueActRelaList!=null && clueActRelaList.size()>0){
            for (ClueActivityRelation car:clueActRelaList) {
                ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();

                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setActivityId(car.getActivityId());
                contactsActivityRelation.setContactsId(co.getId());
                contactsActivityRelationMapper.insert(contactsActivityRelation);
            }
        }


        //如果要创建交易，则往交易表中添加一条记录，把线索表的记录提取到交易表记录
        if ("true".equals(isCreateTran)){
            Tran tran = new Tran();
            tran.setActivityId((String) map.get("activityId"));
            tran.setContactsId(co.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.formatDateAndTime(new Date()));
            tran.setCustomerId(c.getId());
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setId(UUIDUtils.getUUID());
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setOwner(user.getId());
            tran.setStage((String) map.get("stage"));
            tranMapper.insert(tran);

            if(clueRemarkList!=null&&clueRemarkList.size()>0){
                TranRemark tr=null;
                for(ClueRemark cr:clueRemarkList){
                    tr=new TranRemark();
                    tr.setCreateBy(cr.getCreateBy());
                    tr.setCreateTime(cr.getCreateTime());
                    tr.setEditBy(cr.getEditBy());
                    tr.setEditFlag(cr.getEditFlag());
                    tr.setEditTime(cr.getEditTime());
                    tr.setId(UUIDUtils.getUUID());
                    tr.setNoteContent(cr.getNoteContent());
                    tr.setTranId(tran.getId());
                    tranRemarkMapper.insert(tr);
                }
            }
        }
        //删除线索表
        clueMapper.deleteByPrimaryKey(id);
        //删除线索市场活动关联表
        clueActivityRelationMapper.deleteClueActRelationByClueId(id);
        //删除线索备注表
        clueRemarkMapper.deleteClueRemarkByClueId(id);
    }
}


