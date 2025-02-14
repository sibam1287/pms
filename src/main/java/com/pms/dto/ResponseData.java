package com.pms.dto;


import com.google.gson.Gson;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseData {

    private String status;
    private String message;
    private Object data;
    private String errmsg;
    private String totalRecord;
    public static Gson gson = new Gson();

    public static final List<String> EMPTY_LIST = new ArrayList<>();

    public ResponseData(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseData(String status, String message, Object data, String errmsg) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errmsg = errmsg;
    }

    public static String generateResponseData(String status, String message, Object data) {
        ResponseData responseData = new ResponseData(status, message, data);
        String finalJson = gson.toJson(responseData);
        return finalJson;

    }
    public static String generateSuccessRes(String message, Object data, Object obj) {
        ResponseData responseData = new ResponseData("1", message, data==null?EMPTY_LIST:data);
        String finalJson = gson.toJson(responseData);
        return finalJson;

    }


    public static String generateFailedRes(String message, Object data) {
        ResponseData responseData = new ResponseData("0", message, EMPTY_LIST);
        String finalJson = gson.toJson(responseData);
        return finalJson;

    }
    public static String generateFailedRes(String message, Object data, String errmsg) {
        ResponseData responseData = new ResponseData("0", message,EMPTY_LIST, errmsg);
        String finalJson = gson.toJson(responseData);
        return finalJson;

    }



    public static String generateSuccessRes(String message, Object data) {
        ResponseData responseData = new ResponseData("1", message, data == null ? EMPTY_LIST : data);

        String finalJson = gson.toJson(responseData);
        return finalJson;
    }

}

