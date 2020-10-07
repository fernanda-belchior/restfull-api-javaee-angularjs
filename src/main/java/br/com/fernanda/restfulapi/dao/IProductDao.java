package br.com.fernanda.restfulapi.dao;

import br.com.fernanda.restfulapi.model.Product;

import java.util.List;

public interface IProductDao {

    void record (Product product) throws Exception;
    void update (Product product) throws Exception;
    void remove (Product product) throws Exception;
    Product findByName(String name) throws Exception;
    Product findById (int id) throws Exception;
    List<Product> findAll () throws Exception;
}
