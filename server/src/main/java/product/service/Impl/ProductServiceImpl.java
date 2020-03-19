package product.service.Impl;

import com.product.common.ProductInfoOutput;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product.DTO.CartDTO;
import product.Dao.ProductInfoRepository;
import product.Enums.ProductStatusEnum;
import product.Enums.ResultEnum;
import product.dataObject.ProductInfo;
import product.exception.ProductException;
import product.service.ProductService;
import product.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    /***
     * 扣库存并发送mq消息
     * @param cartDTOList
     */
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        List<ProductInfo> productInfos = decreaseStockProcess(cartDTOList);

        //将ProductInfo转为ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputs = productInfos.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());

        //发送mq消息到order服务中
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputs));
    }

    /***
     * 扣库存操作
     * @param cartDTOList
     */
    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfos = new ArrayList<>();
        for (CartDTO cartDTO:cartDTOList){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.productId);
            //判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //判断商品数量是否足够
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //扣库存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfos.add(productInfo);
        }
        return productInfos;
    }

}
