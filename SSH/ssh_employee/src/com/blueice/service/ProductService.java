package com.blueice.service;

import com.blueice.dao.ProductDao;
import com.blueice.domain.Product;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品管理的业务层类
 */

@Transactional //添加事件管理
public class ProductService {

    //业务层注放dao类
    private ProductDao productDao;

    //提供相应的set方法，spring就可以完成相应的注入。
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }


    public void save(Product product){
        System.out.println("Service中的save方法被调用了...");
        productDao.save(product);
    }
}
