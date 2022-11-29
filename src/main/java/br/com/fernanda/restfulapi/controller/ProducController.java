package br.com.fernanda.restfulapi.controller;

import br.com.fernanda.restfulapi.entity.Product;
import br.com.fernanda.restfulapi.service.ProductService;
import br.com.fernanda.restfulapi.service.impl.ProductServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
public class ProducController {

    private final ProductService productBean = new ProductServiceImpl();

    @POST
    @Path("/save")
    public Response save(Product product) {
        productBean.save(product);
        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @PUT
    @Path("/update")
    public void update(Product product) {
        productBean.update(product);
    }

    @DELETE
    @Path("/delete")
    public void delete(Product product) {
        productBean.remove(product);
    }

    @GET
    @Path("/list")
    public List<Product> getProducts() {
        return productBean.findAll();
    }

    @GET
    @Path("/productbyid/{id}")
    public Product getProduct(@PathParam("id") int id) {
        return productBean.findById(id);
    }

    @GET
    @Path("/productbyname/{name}")
    public Product getProduct(@PathParam("name") String name) {
        return productBean.findByName(name);
    }


}