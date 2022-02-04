package cn.chamas.handler;

import cn.chamas.domain.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 自定义全局异常类
@ControllerAdvice
@ResponseBody
public class NotFoundException {
    @Autowired
    private ResultBody resultBody;

    @ExceptionHandler(Exception.class)
    public ResultBody globalException(Exception e){
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            resultBody.ready()
                    .setCode(404);
        }else{
            resultBody.ready()
                    .setCode(500);
        }
        return resultBody.setMsg(e.getMessage());
    }
}
