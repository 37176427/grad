package com.grad.service;

import com.grad.common.utils.GsonUtil;
import com.grad.dao.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 ：StatService
 * 作者 ：WangYunHe
 * 时间 ：2018/4/24 10:49
 **/
@Service
public class StatService {

    @Autowired
    private StatDao statDao;

    /**
     * 统计材料状态
     */
    public String getStatByMaterial() {
        List<MetaData> list = new ArrayList<>();
        MetaData metaData1 = new MetaData();
        MetaData metaData2 = new MetaData();
        metaData1.setName("已有材料");
        metaData1.setValue(statDao.statByMaterialYes());
        metaData2.setName("暂无材料");
        metaData2.setValue(statDao.statByMaterialNo());
        list.add(metaData1);
        list.add(metaData2);
        return GsonUtil.getJsonStringByObject(list);
    }

    /**
     * 统计审核状态
     */
    public String getStatByChecked() {
        Integer[] ints = new Integer[4];
        for (CheckStatus checkType : CheckStatus.values()) {
            int value = statDao.statByChecked(checkType.getStatus());
            ints[checkType.getStatus()] = value;
        }
        return GsonUtil.getJsonStringByObject(ints);
    }

    /**
     * 元数据
     */
    class MetaData {
        private int value;
        private String name;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 审核状态枚举
     */
    enum CheckStatus {
        not(0, "未审核"),
        yes(1, "审核通过"),
        no(2, "审核未通过");
        private int status;
        private String desc;

        CheckStatus(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }
    }
}
