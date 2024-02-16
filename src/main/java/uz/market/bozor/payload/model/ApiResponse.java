package uz.market.bozor.payload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Object data;
    private String message;
    private boolean status;

    public static ApiResponse successResponse(Object data, String message) {
        return new ApiResponse(data, message, true);
    }

    public static ApiResponse successResponse(String message) {
        return new ApiResponse(null, message, true);
    }

    public static ApiResponse failResponse(String message) {
        return new ApiResponse(null, message, false);
    }

    public static ApiResponse failResponse(Object data, String message) {
        return new ApiResponse(data, message, false);
    }

}
