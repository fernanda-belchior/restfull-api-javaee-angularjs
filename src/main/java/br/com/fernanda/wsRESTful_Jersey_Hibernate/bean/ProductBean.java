package br.com.fernanda.wsRESTful_Jersey_Hibernate.bean;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.dao.IProductDao;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.dao.ProductDao;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;


import java.util.List;


public class  ProductBean implements IProductBean {

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
    public Product findById(long id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}


