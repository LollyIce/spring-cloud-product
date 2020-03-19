package product.service;


import product.DTO.CartDTO;
import product.dataObject.ProductInfo;

import java.util.List;

public interface ProductService {

    //查询所有在架商品列表
    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdList);
    //扣库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
