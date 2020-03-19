package product.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import product.dataObject.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
