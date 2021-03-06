package com.geek.conding.business.config;

import com.geek.conding.business.base.GeekException;
import com.geek.conding.business.constants.enums.GeekExceptionMsg;
import com.geek.conding.business.constants.response.RenderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 张耀烽
 * @Date Created in 2020/4/3 23:42
 * @Version v1.0
 * @Description 全局异常处理
 */
@ControllerAdvice
public class GeekControllerAdvice {

    @ResponseBody
    @ExceptionHandler(GeekException.class)
    public ResponseEntity<GeekExceptionMsg> geekException(GeekException e, HttpServletRequest request) {
        //构建请求的URI 写入到Redis队列中
        return RenderResponse.responseErrorMsg(e.getCode(), e.getMessage(), e.getHttpStatus(), e.getRequestId());
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeekExceptionMsg> sysException(Exception e, HttpServletRequest request) {
        //构建请求的URI 写入到Redis队列中
        return RenderResponse.responseErrorMsg(GeekExceptionMsg.SERVER_ERROR);
    }
}
