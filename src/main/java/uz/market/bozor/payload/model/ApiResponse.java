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

    public ApiResponse(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
