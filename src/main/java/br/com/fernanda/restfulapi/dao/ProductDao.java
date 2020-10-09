package br.com.fernanda.restfulapi.dao;

import br.com.fernanda.restfulapi.dto.ProductDTO;

import java.util.List;

public interface ProductDao {

    void record (ProductDTO productDTO);
    void update (ProductDTO productDTO);
    void remove (ProductDTO productDTO);
    ProductDTO findByName(String name);
    ProductDTO findById (int id);
    List<ProductDTO> findAll();
}
