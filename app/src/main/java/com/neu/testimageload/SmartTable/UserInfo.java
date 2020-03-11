package com.neu.testimageload.SmartTable;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * created by Viki on 2020/2/26
 * system login name : lg
 * created time : 15:16
 * email : 710256138@qq.com
 */
@SmartTable(name = "销售计划表")
public class UserInfo {
    public UserInfo(String city, int name, int count, int restaurant, int ka, int wholesale, int industry, int other) {
        this.city = city;
        this.name = name;
        this.count = count;
        this.restaurant = restaurant;
        this.ka = ka;
        this.wholesale = wholesale;
        this.industry = industry;
        this.other = other;
    }

    //    name：版块名称，count：目标值，restaurant：餐饮数量，ka：KA数量，wholesale：流通批发数量，industry：工业加工数量，other：其它数量
    @SmartColumn(id = 0, name = "部门/渠道", autoMerge = true)
    private String city;
    @SmartColumn(id = 1, name = "板块")
    private int name;
    @SmartColumn(id = 2, name = "目标值")
    private int count;
    @SmartColumn(id = 3, name = "餐饮")
    private int restaurant;
    @SmartColumn(id = 4, name = "KA")
    private int ka;
    @SmartColumn(id = 5, name = "流通批发")
    private int wholesale;
    @SmartColumn(id = 6, name = "工业加工")
    private int industry;
    @SmartColumn(id = 7, name = "其他")
    private int other;
}
