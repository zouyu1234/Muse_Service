/***
 *  created by wangwei at 2014-3-25
 *  Response用户返回数据，包括状态，数据对象(List, Entity)
 */
package com.zy.tools;

import com.zy.enums.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@SuppressWarnings("serial")
public class Response extends HashMap<String,Object> {

    private final static String CODE = "code";
    private final static String DATA = "data";
    private final static String TOTAL = "total";
    private final static String MESSAGE = "msg";

    private final static String WRCBCODE = "respCode";
    private final static String WRCBCODE_SUCCESS = "0000";
    private final static String WRCBMESSAGE = "respMsg";
    private final static String WRCBMESSAGE_SUCCESS = "ok";

    private Logger logger = LoggerFactory.getLogger(Response.class);

    private Response() {
        this.put(CODE, ErrorEnum.SUCCESS.getCode());
        this.put(MESSAGE, ErrorEnum.SUCCESS.getMessage());
    }

    private Response(String code, String msg) {
        this.put(code, WRCBCODE_SUCCESS);
        this.put(msg, WRCBMESSAGE_SUCCESS);
    }

    public static Response WRCBResponse(){
        Response response = new Response(WRCBCODE,WRCBMESSAGE);
        return response;
    }

    //静态方法------------------------------------------------------------------------------
    public static Response newResponse() {
        return new Response();
    }

    public static Response set(Integer total, Object rows) {
        Response response = new Response();
        response.setTotal(total);
        response.ok(rows);

        return response;
    }

    public static Response set(Object rows) {
        Response response = new Response();
        response.ok(rows);

        return response;
    }

    public static Response set(String key, Object value) {
        Response response = Response.newResponse();
        response.put(key, value);
        return response;
    }

    /**
     * 更新是否成功
     * @author guoyu
     */
    public static Response updateIsSuccess(boolean isSuccess){
        Response response = Response.newResponse();
        if (isSuccess){
            return response.OK();
        }else{
            response.setError(ErrorEnum.PARAM_ERROR);
            response.setMessage("更新失败,可能是您提交的数据没有修改");
            return response;
        }
    }

    //静态方法---------------------------------------------------------------------------------------------




    /**获得一个可强制转型的泛型值
     * @author guoyu
     */
    @SuppressWarnings("unchecked")
    public <T> T getGenericType(Object key){
        return (T)this.get(key);
    }

    /**
     * @author fubiao
     * @param data
     */
    public Response setData(Object data){
        this.put(DATA, data);
        return this;
    }

    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获得错误消息
     * @author guoyu
     */
    public String getMessage() {
        Object msg = this.get(MESSAGE);
        return msg != null ? msg.toString() : null;
    }

    public Response moveTo(String fromKey, String toKey) {
        Object val = this.get(fromKey);
        this.put(toKey, val);
        this.remove(fromKey);
        return this;
    }

    public Boolean isOK() {
        return this.getCode() == ErrorEnum.SUCCESS.getCode();
    }

    public Boolean isFail() {
        return !isOK();
    }

    public Response ok(Object data) {
        super.put(DATA, data);
        return this;
    }

    public Response ok(String key, Object val) {
        super.put(key, val);
        return this;
    }

    public Response setResults(Integer count, Object data) {
        this.setTotal(count);
        this.ok(data);
        return this;
    }

    private Response setCode(int code) {
        this.put(CODE, code);
        return this;
    }

    /**
     * 设置返回码与返回信息
     *
     * 强列建议在ErrorEnum 中定义错误, 再调用setError()方法
     *
     * @param code
     * @param message
     * @return
     * @author guoyu
     */
    public Response setCodeAndMessage(int code,String message) {
        this.put(CODE, code);
        this.put(MESSAGE, message);
        return this;
    }

    public Response wrcbRspFail() {
        this.put(WRCBCODE, "9999");
        this.put(WRCBMESSAGE, "fail");
        return this;
    }

    /**
     * 设置返回信息
     * 此方法只能用于标明一些已有错误code,但需要特别定制的返回消息时用到
     * 请不要滥用此方法
     *
     * @param message
     * @return
     * @author guoyu
     */
    public Response setMessage(String message) {
        this.put(MESSAGE, message);
        return this;
    }

    public int getCode() {
        return Integer.parseInt(this.get(CODE).toString());
    }

    /** 1: 发送异常邮件到开发者,
     * 	2: 发送短信通知,
     * 	3: 打印异常栈信息,
     * 	4: 记录异常日志,
     * 	5: 返回错误代码到前台.
     * @param e
     * @return
     * @author guoyu
     */
    public Response error(Throwable e) {

        //发送异常邮件到开发者
//		MailUtil.sendMail(e);

        //发送短信通知
        //SendSMS.send("18601694368", "CRM系统出现故障");

        //打印异常栈信息到控制台
        e.printStackTrace();

        //记录异常日志
        logger.error("ExceptionError",e);

        //返回错误代码到前台
        this.setCode(ErrorEnum.SERVER_ERROR.getCode());
        this.put(MESSAGE, ErrorEnum.SERVER_ERROR.getMessage());

        return this;
    }

    public Response setTotal(Integer total) {
        this.put(TOTAL, total);
        return this;
    }


//============状态===================================================================================	

    public Response OK() {
        this.setCode(ErrorEnum.SUCCESS.getCode());
        this.put(MESSAGE, "ok");
        return this;
    }

    /**
     * 设置错误类型
     * 传入ErrorEnum 枚举
     * @author guoyu
     */
    public Response setError(ErrorEnum errorEnum){
        this.setCode(errorEnum.getCode());
        this.setMessage(errorEnum.getMessage());
        if(errorEnum.getCnMessage() != null)
            this.put("cn_message",errorEnum.getCnMessage());

        return this;
    }

    /**
     * 设置错误类型
     * 传入ErrorEnum 枚举, 加上自定义错误信息
     * @author guoyu
     */
    public Response setError(ErrorEnum errorEnum, String msg){
        this.setCode(errorEnum.getCode());
        String message = errorEnum.getMessage();
        if(msg != null){
            message += (" " + msg);
        }
        this.setMessage(message);
        if(errorEnum.getCnMessage() != null)
            this.put("cn_message",errorEnum.getCnMessage());

        return this;
    }


}