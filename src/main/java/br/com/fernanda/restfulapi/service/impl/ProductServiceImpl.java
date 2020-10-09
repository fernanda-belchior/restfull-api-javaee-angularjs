package br.com.fernanda.restfulapi.service.impl;

import br.com.fernanda.restfulapi.dao.ProductDao;
import br.com.fernanda.restfulapi.dao.impl.ProductDaoImpl;
import br.com.fernanda.restfulapi.dto.ProductDTO;
import br.com.fernanda.restfulapi.service.ProductService;


import java.util.List;


public class ProductServiceImpl implements ProductService {

   private ProductDao productDao = new ProductDaoImpl();


    @Override
    public void record(ProductDTO productDTO){
        productDao.record(productDTO);
    }

    @Override
    public void update(ProductDTO productDTO){
        productDao.update(productDTO);
    }

    @Override
    public void remove(ProductDTO productDTO){
        productDao.remove(productDTO);
    }

    @Override
    public ProductDTO findByName(String name){
        return productDao.findByName(name);
    }

    @Override
    public ProductDTO findById(int id){
        return productDao.findById(id);
    }

    @Override
    public List<ProductDTO> findAll(){
        return productDao.findAll();
    }
}


