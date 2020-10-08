package br.com.fernanda.restfulapi.service;

import br.com.fernanda.restfulapi.dao.IProductDao;
import br.com.fernanda.restfulapi.dao.ProductDao;
import br.com.fernanda.restfulapi.model.Product;


import java.util.List;


public class ProductService implements IProductService {

   private IProductDao productDao = new ProductDao();


    @Override
    public void record(Product product) throws Exception {
        productDao.record(product);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public void remove(Product product) throws Exception {
        productDao.remove(product);
    }

    @Override
    public Product findByName(String name) throws Exception {
        return productDao.findByName(name);
    }

    @Override
    public Product findById(int id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}


