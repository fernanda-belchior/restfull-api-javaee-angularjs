package br.com.fernanda.wsRESTful_Jersey_Hibernate.webservice;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.IProductBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.ProductBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/wsRESTful_Jersey_Hibernate")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProductService {
    
    private IProductBean productBean = new ProductBean();
    
    @GET
    public List<Product> getProducts() throws Exception {
        return productBean.findAll();
    }

    @GET
    @Path("{productId}")
    public Product getProduct(@PathParam("productId") long id) throws Exception {
        return productBean.findById(id);
    }

    @GET
    @Path("{name}")
    public Product getProduct(@PathParam("name") String name) throws Exception {
        return productBean.findByName(name);
    }

    @POST
    public void save(Product product) throws Exception {
        productBean.record(product);

    }

    @PUT
    public void update( Product product) throws Exception {
        productBean.update(product);
    }

    @DELETE
    public void delete(Product product) throws Exception {
        productBean.remove(product);
    }

}
