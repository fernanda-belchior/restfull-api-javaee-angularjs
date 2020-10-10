package br.com.fernanda.restfulapi.dao.impl;

import br.com.fernanda.restfulapi.dao.ProductDao;
import br.com.fernanda.restfulapi.entity.Product;
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
    public void record(Product product)  {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();


        try {
            productIsValid(product);
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new DaoException("Error saving product to database:"+e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }


    }

    @Override
    public void update(Product product)  {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        Product productFind = new Product();

        if (product.getId() <= 0) {
            throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        try {
            productIsValid(product);
            em.getTransaction().begin();
            productFind = this.findById(product.getId());
            productFind.setName(product.getName());
            productFind.setQuantity(product.getQuantity());
            productFind.setValue(product.getValue());
            em.merge(productFind);
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
    public void remove(Product product) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();

        if (product.getId() <= 0) {
            throw new DaoException("The id must be greater than 0", ErrorCode.BAD_REQUEST.getCode());
        }
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE  FROM Product where id = :idProduct ");
            query.setParameter("idProduct", product.getId());
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
    public Product findByName(String name) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;
        Product product = null;

        try {

            em.getTransaction().begin();
            String sql = "SELECT product from Product product where product.name = :name";
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
        if (!list.isEmpty()) {
            product = list.get(0);
        }else {
            throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }
        if (product ==null){
            throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }

        return product;

    }


    @Override
    public Product findById(int id) {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;
        Product product = null;

        if (id <= 0) {
            throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }


        try {
            em.getTransaction().begin();
            String sql = "SELECT product from Product product WHERE product.id = :id";
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

        if (!list.isEmpty()) {
            product = list.get(0);
        }else {
            throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }
        if (product ==null){
            throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }

        return product;

    }


    @Override
    public List<Product> findAll() {
        EntityManager em = JpaEntityManager.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;


        try {
            em.getTransaction().begin();
            String sql = "SELECT product from Product product";
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

    private boolean productIsValid(Product product) {
        try {
            if ((product.getName().isEmpty()) || (product.getQuantity() < 0)) {
                return false;
            }
        } catch (NullPointerException e) {
            throw new DaoException("Product with incomplete data", ErrorCode.BAD_REQUEST.getCode());
        }

        return true;
    }


}
