package product.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import product.dataObject.ProductInfo;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //查询所有在架的字段
    List<ProductInfo>  findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
