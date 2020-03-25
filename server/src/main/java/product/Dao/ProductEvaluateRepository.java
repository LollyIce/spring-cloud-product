package product.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import product.dataObject.ProductEvaluate;

import java.util.List;

public interface ProductEvaluateRepository extends JpaRepository<ProductEvaluate,String> {

    List<ProductEvaluate> findByProductIdIn(List<String> ids);
}
