package com.github.allisson95.javawebangular.controller;

import java.util.List;
import java.util.UUID;

import com.github.allisson95.javawebangular.model.Product;
import com.github.allisson95.javawebangular.service.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("products")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductController {

    private final ProductService productService;

    public ProductController() {
        super();
        this.productService = null;
    }

    @Inject
    public ProductController(final ProductService productService) {
        super();
        this.productService = productService;
    }

    @GET
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") UUID id) {
        try {
            Product product = this.productService.find(id);
            return Response.ok(product).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
