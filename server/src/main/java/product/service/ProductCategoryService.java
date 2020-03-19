package product.service;


import product.dataObject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
