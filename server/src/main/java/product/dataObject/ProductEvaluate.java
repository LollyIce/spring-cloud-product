package product.dataObject;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@ToString
public class ProductEvaluate {

    @Id
    private String evaluateId;
    //用户ID
    private String userId;
    //商品ID
    private String productId;
    //评论时间
    private Date rateTime;
    //满意度
    private int rateType;
    //评论内容
    private String text;

}
