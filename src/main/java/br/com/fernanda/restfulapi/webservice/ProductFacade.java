package br.com.fernanda.restfulapi.webservice;

import br.com.fernanda.restfulapi.service.IProductService;
import br.com.fernanda.restfulapi.service.ProductService;
import br.com.fernanda.restfulapi.model.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
public class ProductFacade {

    private IProductService productBean = new ProductService();

    @POST
    @Path("/save")
    public Response save(Product product) throws Exception {
        productBean.record(product);
        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @PUT
    @Path("/update")
    public void update(Product product) throws Exception {
        productBean.update(product);
    }

    @DELETE
    @Path("/delete")
    public void delete(Product product) throws Exception {
        productBean.remove(product);
    }

    @GET
    @Path("/list")
    public List<Product> getProducts() throws Exception {
        return productBean.findAll();
    }

    @GET
    @Path("/productbyid/{id}")
    public Product getProduct(@PathParam("id") int id) throws Exception {
        return productBean.findById(id);
    }

    @GET
    @Path("/productbyname/{name}")
    public Product getProduct(@PathParam("name") String name) throws Exception {
        return productBean.findByName(name);
    }





}