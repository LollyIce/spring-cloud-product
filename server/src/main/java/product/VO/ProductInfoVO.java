package product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    private BigDecimal oldPrice;

    @JsonProperty("description")
    private String productDescription;

    //月售
    private Integer sellCount;

    //好评率
    private Integer rating;

    private String info;

    @JsonProperty("icon")
    private String productIcon;

    private String image;

    private List<ProductEvaluateVO> ratings;
}
