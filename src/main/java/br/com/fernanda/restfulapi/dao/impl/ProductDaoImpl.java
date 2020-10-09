package br.com.fernanda.restfulapi.dao.impl;

import br.com.fernanda.restfulapi.dao.ProductDao;
import br.com.fernanda.restfulapi.dto.ProductDTO;
import br.com.fernanda.restfulapi.entitymanager.JpaEntityManager;
import br.com.fernanda.restfulapi.exceptions.DaoException;
import br.com.fernanda.restfulapi.exceptions.ErrorCode;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import java.util.List;


public class ProductDaoImpl implements ProductDao {

    private static final String ERROR_FIND_BY_ID = "Error fetching product by id in the database:";
    private static final String ERROR_FIND_BY_NAME = "Error fetching product by name in the database:";
    private static final String ERROR_FIND_ALL = "Error retrieving data from database";


    @Override
    public void record(ProductDTO productDTO)  {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();


        try {
            productIsValid(productDTO);
            em.getTransaction().begin();
            em.persist(productDTO);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new DaoException("Error saving product to database:"+e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }


    }

    @Override
    public void update(ProductDTO productDTO)  {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        ProductDTO productDTOFind = new ProductDTO();

        if (productDTO.getId() <= 0) {
            throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        try {
            productIsValid(productDTO);
            em.getTransaction().begin();
            productDTOFind = this.findById(productDTO.getId());
            productDTOFind.setName(productDTO.getName());
            productDTOFind.setQuantity(productDTO.getQuantity());
            productDTOFind.setValue(productDTO.getValue());
            em.merge(productDTOFind);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            em.getTransaction().rollback();
            throw new DaoException("Product reported for update does not exist:"+e.getMessage(),ErrorCode.NOT_FOUND.getCode());
        } catch (RuntimeException e){
            throw new DaoException("Error updating product in the database:"+e.getMessage(),ErrorCode.SERVER_ERROR.getCode());

        }finally {
            em.close();
        }

    }

    @Override
    public void remove(ProductDTO productDTO) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();

        if (productDTO.getId() <= 0) {
            throw new DaoException("The id must be greater than 0", ErrorCode.BAD_REQUEST.getCode());
        }
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE  FROM ProductDTO where id = :idProduct ");
            query.setParameter("idProduct", productDTO.getId());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw new DaoException("Product reported for removal does not exist:"+ e.getMessage(), ErrorCode.NOT_FOUND.getCode());
        } catch (RuntimeException e){
            throw new DaoException("Error to remove the product from the database"+e.getMessage(), ErrorCode.SERVER_ERROR.getCode());

        } finally {
            em.close();
        }

    }


    @Override
    public ProductDTO findByName(String name) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<ProductDTO> list = null;
        ProductDTO productDTO = null;

        try {

            em.getTransaction().begin();
            String sql = "SELECT product from ProductDTO product where product.name = :name";
            Query query = em.createQuery(sql);
            query.setParameter("name", name);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new DaoException(ERROR_FIND_BY_NAME+ e.getMessage(), ErrorCode.NOT_FOUND.getCode());
        } finally {
            em.close();
        }
        if (list != null && list.size() > 0) {
            productDTO = list.get(0);
        }else {
            throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }
        if (productDTO ==null){
            throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }

        return productDTO;

    }


    @Override
    public ProductDTO findById(int id) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<ProductDTO> list = null;
        ProductDTO productDTO = null;

        if (id <= 0) {
            throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }


        try {
            em.getTransaction().begin();
            String sql = "SELECT product from ProductDTO product WHERE product.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", id);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new DaoException(ERROR_FIND_BY_ID + e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }

        if (list != null && list.size() > 0) {
            productDTO = list.get(0);
        }else {
            throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }
        if (productDTO ==null){
            throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }

        return productDTO;

    }


    @Override
    public List<ProductDTO> findAll() {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<ProductDTO> list = null;


        try {
            em.getTransaction().begin();
            String sql = "SELECT product from ProductDTO product";
            Query query = em.createQuery(sql);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new DaoException(ERROR_FIND_ALL + e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }

        if(list.isEmpty()){
            throw new DaoException(ERROR_FIND_ALL, ErrorCode.NOT_FOUND.getCode());

        }

        return list;

    }

    private boolean productIsValid(ProductDTO productDTO) {
        try {
            if ((productDTO.getName().isEmpty()) || (productDTO.getQuantity() < 0)) {
                return false;
            }
        } catch (NullPointerException e) {
            throw new DaoException("Product with incomplete data", ErrorCode.BAD_REQUEST.getCode());
        }

        return true;
    }


}
