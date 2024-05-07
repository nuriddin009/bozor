package uz.market.bozor.filter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import uz.market.bozor.entity.constants.Language;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ApiModel(description = "Product parameters")
@ToString(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class CategoryFilter extends PageFilter {


    @ApiModelProperty(value = "The Search Key filter")
    @ToString.Include
    private String search = "";

    @ApiModelProperty(value = "product language")
    @ToString.Include
    private Language language;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getSearchForQuery() {
        return StringUtils.isNotEmpty(search) ? "%" + search.toLowerCase().replace("_", "\\_") + "%" : search;
    }

}
