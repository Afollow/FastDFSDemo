package cn.chamas.domain;

import org.springframework.stereotype.Component;

@Component
public class ResultBody {
    private Integer code = 200;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public ResultBody setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBody setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultBody setData(Object data) {
        this.data = data;
        return this;
    }

    public ResultBody ready(){
        this.code = 200;
        this.msg = null;
        this.data = null;
        return this;
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
