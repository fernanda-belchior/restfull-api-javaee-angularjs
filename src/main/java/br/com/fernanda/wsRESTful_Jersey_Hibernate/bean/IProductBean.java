package br.com.fernanda.wsRESTful_Jersey_Hibernate.bean;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;

import java.util.List;

public interface IProductBean {

    void record (Product product) throws Exception;
    void update (Product product) throws Exception;
    void remove (Product product) throws Exception;
    Product findByName(String Name) throws Exception;
    Product findById (int id) throws Exception;
    List<Product> findAll () throws Exception;
}
