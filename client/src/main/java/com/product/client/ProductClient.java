package com.product.client;

import com.product.common.DecreaseStockinput;
import com.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockinput> decreaseStockinputList);
}
