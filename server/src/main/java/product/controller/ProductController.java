package product.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.DTO.CartDTO;
import product.VO.ProductInfoVO;
import product.VO.ProductVO;
import product.VO.ResultVO;
import product.dataObject.ProductCategory;
import product.dataObject.ProductInfo;
import product.service.ProductCategoryService;
import product.service.ProductService;
import product.utils.ResultVOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    /***
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3。查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO list(){
        //查询所有在架商品
        List<ProductInfo> upAll = productService.findUpAll();
        //获取在架商品中的Type列表
        List<Integer> categoryTypeList = upAll.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        //查询类目
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        /**拼装数据*/
        //返回的结果集
        ResultVO resultVO = new ResultVO();

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:upAll) {
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtil.success(productVOList);
    }

    /***
     * 获取商品列表（给订单服务用）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return productService.findList(productIdList);
    }

    /***
     * 扣库存
     * @param cartDTOList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }
}
