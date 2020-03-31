package br.com.fernanda.wsRESTful_Jersey_Hibernate.webservice;

import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.IProductBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.bean.ProductBean;
import br.com.fernanda.wsRESTful_Jersey_Hibernate.model.Product;
import javax.ws.rs.*;
import java.util.List;

@Path("/product")
public class ProductService {

    private IProductBean productBean = new ProductBean();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getProducts")
    public List<Product> getProducts() throws Exception {
        return productBean.findAll();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getProductById/{id}")
    public Product getProduct(@PathParam("id") int id) throws Exception {
        return productBean.findById(id);
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getProductByName/{name}")
    public Product getProduct(@PathParam("name") String name) throws Exception {
        return productBean.findByName(name);
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/save")
    public void save(Product product) throws Exception {
        productBean.record(product);
    }

    @PUT
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/update")
    public void update(Product product) throws Exception {
        productBean.update(product);
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/delete")
    public void delete(Product product) throws Exception {
        productBean.remove(product);
    }

}