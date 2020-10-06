package br.com.fernanda.wsrestful.dao;

import br.com.fernanda.wsrestful.model.Product;

import java.util.List;

public interface IProductDao {

    void record (Product product) throws Exception;
    void update (Product product) throws Exception;
    void remove (Product product) throws Exception;
    Product findByName(String name) throws Exception;
    Product findById (int id) throws Exception;
    List<Product> findAll () throws Exception;
}
