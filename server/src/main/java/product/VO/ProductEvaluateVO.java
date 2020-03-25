package product.VO;

import lombok.Data;

import java.util.Date;

@Data
public class ProductEvaluateVO {

    private String username;
    //评论时间
    private Date rateTime;
    //满意度
    private int rateType;
    //评论内容
    private String text;
    //用户头像
    private String avatar;
}
