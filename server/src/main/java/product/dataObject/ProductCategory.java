package product.dataObject;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@ToString
@Entity
public class ProductCategory {
    //主键
    @Id
    @GeneratedValue    //自增
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}
