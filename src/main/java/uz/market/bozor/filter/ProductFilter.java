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
public class ProductFilter extends PageFilter {


    @ApiModelProperty(value = "The Search Key filter")
    @ToString.Include
    private String search = "";

    @ApiModelProperty(value = "Min price of products")
    @ToString.Include
    private BigDecimal minPrice;
    @ApiModelProperty(value = "Max price of products")
    @ToString.Include
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "Category ids")
    @ToString.Include
    private List<UUID> categoryIds;

    @ApiModelProperty(value = "Get products by it's colors")
    @ToString.Include
    private List<String> colors;

    @ApiModelProperty(value = "product language")
    @ToString.Include
    private Language language;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getSearchForQuery() {
        return StringUtils.isNotEmpty(search) ? "%" + search.toLowerCase().replace("_", "\\_") + "%" : search;
    }

}
