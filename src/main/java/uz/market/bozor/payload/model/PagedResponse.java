package uz.market.bozor.payload.model;

import lombok.Getter;
import lombok.Setter;
import uz.market.bozor.payload.response.Paging;

@Getter
@Setter
public class PagedResponse<T> extends BaseResponse<T> {
    private Paging paging;
}
