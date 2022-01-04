package com.wiki.exception;



public class BusinessException extends RuntimeException {

    private BusinessExceptionCode mes;
    private Integer code;

    public BusinessException(BusinessExceptionCode mes, Integer code) {
        super(mes.getDesc());
        this.mes = mes;
        this.code = code;
    }

    public BusinessExceptionCode getMes() {
        return mes;
    }

    public void setMes(BusinessExceptionCode mes) {
        this.mes = mes;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
