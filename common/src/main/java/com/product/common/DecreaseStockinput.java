package com.product.common;

import lombok.Data;

@Data
public class DecreaseStockinput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockinput() {
    }

    public DecreaseStockinput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
