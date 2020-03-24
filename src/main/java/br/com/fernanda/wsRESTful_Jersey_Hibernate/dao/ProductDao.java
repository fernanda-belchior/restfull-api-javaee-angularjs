package br.com.fernanda.wsRESTful_Jersey_Hibernate.dao;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.JpaResourceBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductDao implements IProductDao {

    @Override
    public void record(Product product) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
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
            em.getTransaction().begin();
            productFind = this.findById(product.getId());
            productFind.setName(product.getName());
            productFind.setQuantity(product.getQuantity());
            productFind.setValue(product.getValue());
            em.merge(productFind);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

    }

    @Override
    public void remove(Product product) throws Exception {
        EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE  FROM Product where id = :idProduct ");
            query.setParameter("idProduct", product.getId());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
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
            String sql = "SELECT product from Product product WHERE product.name = :name";
            Query query = em.createQuery(sql);
            query.setParameter("name", name);
            list = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
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
            em.getTransaction().begin();
            String sql = "SELECT product from Product product WHERE product.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
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
            em.getTransaction().begin();
            String sql = "SELECT product from Product product";
            Query query = em.createQuery(sql);
            list = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }

        return list;

    }
}
