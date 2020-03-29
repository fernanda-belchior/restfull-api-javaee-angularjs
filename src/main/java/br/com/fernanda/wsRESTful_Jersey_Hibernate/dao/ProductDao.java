package br.com.fernanda.wsRESTful_Jersey_Hibernate.dao;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.JpaResourceBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;

import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.*;

import java.util.List;

@TransactionManagement
public class ProductDao implements IProductDao {

    @Inject
    UserTransaction userTransaction;

    public UserTransaction getUserTransaction() {
        return this.getUserTransaction();
    }


    @Override
    public void record(Product product) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();


        try{
            this.getUserTransaction().begin();
            em.persist(product);
            this.getUserTransaction().commit();
        }catch (Exception e){
            throw new Exception( e);
        }finally{
            em.close();
        }


    }

    @Override
    public void update(Product product) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
        Product productFind = new Product();

        try {
            this.getUserTransaction().begin();
            productFind = this.findById(product.getId());
            productFind.setName(product.getName());
            productFind.setQuantity(product.getQuantity());
            productFind.setValue(product.getValue());
            em.merge(productFind);
            this.getUserTransaction().commit();
        } catch (Exception e) {
            this.getUserTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

    }

    @Override
    public void remove(Product product) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            this.getUserTransaction().begin();
            Query query = em.createQuery("DELETE  FROM Product where id = :idProduct ");
            query.setParameter("idProduct", product.getId());
            query.executeUpdate();
            this.getUserTransaction().commit();
        } catch (Exception e) {
            this.getUserTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

    }

    @Override
    public Product findByName(String name) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;
        Product product = null;


        try {
            this.getUserTransaction().begin();
            String sql = "SELECT product from Product product WHERE product.name = :name";
            Query query = em.createQuery(sql);
            query.setParameter("name", name);
            list = query.getResultList();
            this.getUserTransaction().commit();
        } catch (Exception e) {
            this.getUserTransaction().rollback();
            throw new Exception(e);
        }
        finally {
            em.close();
        }
        if (list != null && list.size() > 0) {
            product = list.get(0);
        }

        return product;

    }

    @Override
    public Product findById(long id) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;
        Product product = null;


        try {
            this.getUserTransaction().begin();
            String sql = "SELECT product from Product product WHERE product.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", id);
            list = query.getResultList();
            this.getUserTransaction().commit();
        } catch (Exception e) {
            this.getUserTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

        if (list != null && list.size() > 0) {
            product = list.get(0);
        }

        return product;

    }

    @Override
    public List<Product> findAll() throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
        List<Product> list = null;


        try {
            this.getUserTransaction().begin();
            String sql = "SELECT product from Product product";
            Query query = em.createQuery(sql);
            list = query.getResultList();
            this.getUserTransaction().commit();
        } catch (Exception e) {
            this.getUserTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

        return list;

    }
}
