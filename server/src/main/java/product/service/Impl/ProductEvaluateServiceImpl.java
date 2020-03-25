package product.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Dao.ProductEvaluateRepository;
import product.dataObject.ProductEvaluate;
import product.service.ProductEvaluateService;

import java.util.List;

@Service
public class ProductEvaluateServiceImpl implements ProductEvaluateService {

    @Autowired
    private ProductEvaluateRepository evaluateRepository;

    @Override
    public List<ProductEvaluate> findByIds(List<String> ids) {
        return evaluateRepository.findByProductIdIn(ids);
    }
}
