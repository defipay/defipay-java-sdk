package com.defipay.custody.api.client.domain;


public class ApiResponse<T> {
    T data;
    private boolean success;
    private int code;
    private String msg;

    public ApiResponse(){
    }

    public ApiResponse(T data, boolean success, int code,
                       String msg) {
        this.data = data;
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }





    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
