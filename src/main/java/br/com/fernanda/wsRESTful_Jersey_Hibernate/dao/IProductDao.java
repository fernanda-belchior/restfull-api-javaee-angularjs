package br.com.fernanda.wsRESTful_Jersey_Hibernate.dao;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;

import java.util.List;

public interface IProductDao {

    void record (Product product) throws Exception;
    void update (Product product) throws Exception;
    void remove (Product product) throws Exception;
    Product findByName(String name) throws Exception;
    Product findById (long id) throws Exception;
    List<Product> findAll () throws Exception;
}
