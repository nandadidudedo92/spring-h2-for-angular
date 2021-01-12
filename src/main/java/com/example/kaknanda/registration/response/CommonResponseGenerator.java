package com.example.kaknanda.registration.response;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> CommonResponse<T> commonSuccessResponse(T wrapper, String detail) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setMessage("success");
        commonResponse.setStatus("200");

        if(wrapper!=null) {
            commonResponse.setDetail(detail);
        } else {
            commonResponse.setDetail("Data Not Found");
        }

        commonResponse.setDatas(wrapper);
        return commonResponse;
    }

    public CommonResponse<T> commonFailedError(Exception exception) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setMessage("Failed");
        commonResponse.setStatus("200");
        commonResponse.setDetail(exception.getMessage());
        return commonResponse;
    }

}

