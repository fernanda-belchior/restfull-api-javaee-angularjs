package br.com.fernanda.restfulapi.facade;

import br.com.fernanda.restfulapi.dto.ProductDTO;
import br.com.fernanda.restfulapi.service.ProductService;
import br.com.fernanda.restfulapi.service.impl.ProductServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
public class ProductFacade {

    private ProductService productBean = new ProductServiceImpl();

    @POST
    @Path("/save")
    public Response save(ProductDTO productDTO) {
        productBean.record(productDTO);
        return Response.status(Response.Status.CREATED)
                .entity(productDTO)
                .build();
    }

    @PUT
    @Path("/update")
    public void update(ProductDTO productDTO) {
        productBean.update(productDTO);
    }

    @DELETE
    @Path("/delete")
    public void delete(ProductDTO productDTO) {
        productBean.remove(productDTO);
    }

    @GET
    @Path("/list")
    public List<ProductDTO> getProducts() {
        return productBean.findAll();
    }

    @GET
    @Path("/productbyid/{id}")
    public ProductDTO getProduct(@PathParam("id") int id) {
        return productBean.findById(id);
    }

    @GET
    @Path("/productbyname/{name}")
    public ProductDTO getProduct(@PathParam("name") String name) {
        return productBean.findByName(name);
    }





}