package com.bjpowernode.settings.mapper;

import com.bjpowernode.settings.beans.DicType;

public interface DicTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    int insert(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    int insertSelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    DicType selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    int updateByPrimaryKeySelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Thu Apr 07 21:27:26 CST 2022
     */
    int updateByPrimaryKey(DicType record);
}