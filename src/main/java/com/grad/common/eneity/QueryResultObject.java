package com.grad.common.eneity;

import java.util.Map;

/**
 * 描述 ：返回数据包装类
 * 作者 ：WangYunHe
 * 时间 ：2018/4/16 14:11
 **/
public class QueryResultObject {

    //查询的结果
    private boolean result;
    //查询的数据集合
    private Map<String, ?> data;
    //描述消息
    private String msg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QueryResultObject that = (QueryResultObject) o;

        if (result != that.result) {
            return false;
        }
        if (data != null ? !data.equals(that.data) : that.data != null) {
            return false;
        }
        return msg != null ? msg.equals(that.msg) : that.msg == null;

    }

    @Override
    public int hashCode() {
        int result1 = (result ? 1 : 0);
        result1 = 31 * result1 + (data != null ? data.hashCode() : 0);
        result1 = 31 * result1 + (msg != null ? msg.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "QueryResultObject{" +
                "result=" + result +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
