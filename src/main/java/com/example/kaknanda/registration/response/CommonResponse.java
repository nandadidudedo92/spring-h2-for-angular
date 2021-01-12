package com.example.kaknanda.registration.response;

public class CommonResponse<T> {
    private String status;
    private String message;
    private String detail;
    private T datas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public CommonResponse(String status, String message, String detail, T datas) {
        this.status = status;
        this.message = message;
        this.detail = detail;
        this.datas = datas;
    }

    public CommonResponse(){

    }

}
