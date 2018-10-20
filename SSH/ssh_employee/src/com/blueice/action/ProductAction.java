package com.blueice.action;

import com.blueice.domain.Product;
import com.blueice.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 商品管理Action类
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    //struts和spring的整合
    private ProductService productService;

    //由于引入了struts-sprint-pulgins 的jar包，可以直接使用set方法来完成注入
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //模型驱动使用的类
    private Product product = new Product();

    @Override
    public Product getModel() {
        return product;
    }


    /**
     * 保存商品的请求接收方法
     * @return
     */
    public String save(){
        product.setPname("电视机");
        product.setPrice(13234.56);
        System.out.println("Action中的save方法被调用了。");
        productService.save(product);
        return NONE;
    }
}
