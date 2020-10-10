package br.com.fernanda.restfulapi.service;

import br.com.fernanda.restfulapi.entity.Product;

import java.util.List;

public interface ProductService {

    void record (Product product);
    void update (Product product);
    void remove (Product product);
    Product findByName(String name);
    Product findById (int id);
    List<Product> findAll ();
}
