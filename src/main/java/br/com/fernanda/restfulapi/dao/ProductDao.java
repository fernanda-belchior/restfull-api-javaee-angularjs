package br.com.fernanda.restfulapi.dao;

import br.com.fernanda.restfulapi.entity.Product;

import java.util.List;

public interface ProductDao {

    void save (Product product);
    void update (Product product);
    void remove (Product product);
    Product findByName(String name);
    Product findById (int id);
    List<Product> findAll();
}
