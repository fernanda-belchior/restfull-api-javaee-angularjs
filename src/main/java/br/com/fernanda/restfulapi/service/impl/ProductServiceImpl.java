package br.com.fernanda.restfulapi.service.impl;

import br.com.fernanda.restfulapi.dao.ProductDao;
import br.com.fernanda.restfulapi.dao.impl.ProductDaoImpl;
import br.com.fernanda.restfulapi.entity.Product;
import br.com.fernanda.restfulapi.service.ProductService;


import java.util.List;


public class ProductServiceImpl implements ProductService {

   private ProductDao productDao = new ProductDaoImpl();


    @Override
    public void record(Product product){
        productDao.record(product);
    }

    @Override
    public void update(Product product){
        productDao.update(product);
    }

    @Override
    public void remove(Product product){
        productDao.remove(product);
    }

    @Override
    public Product findByName(String name){
        return productDao.findByName(name);
    }

    @Override
    public Product findById(int id){
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll(){
        return productDao.findAll();
    }

    public static void main(String[] args){
        Product product = new Product();
        product.setId(5);
        product.setName("name");
        product.setQuantity(5);
        product.setValue(5);

        ProductDao productDao = new ProductDaoImpl();
        productDao.record(product);

    }
}


