package uz.market.bozor.component;

import org.springframework.stereotype.Component;
import uz.market.bozor.entity.constants.Response;
import uz.market.bozor.payload.model.BaseResponse;

import java.util.function.Function;

@Component
public class ResponseGenerator {
    public static final Function<String, BaseResponse<String>> generateSuccessResponse = (msg -> {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMessage(Response.BaseResponseMessage.SUCCESS.toString());
        response.setResponseData(msg);
        return response;
    });

    public static final Function<String, BaseResponse<String>> generateFailedResponse = (msg -> {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMessage(Response.BaseResponseMessage.FAILED.toString());
        response.setResponseData(msg);
        return response;
    });
}
