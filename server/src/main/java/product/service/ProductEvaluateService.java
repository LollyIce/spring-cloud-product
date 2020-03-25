package product.service;

import product.VO.ProductInfoVO;
import product.dataObject.ProductEvaluate;

import java.util.List;

public interface ProductEvaluateService {

    List<ProductEvaluate> findByIds(List<String> ids);
}
