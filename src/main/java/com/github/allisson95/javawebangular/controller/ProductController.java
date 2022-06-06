package com.github.allisson95.javawebangular.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.allisson95.javawebangular.model.Product;

@Path("products")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductController {

    private static final Map<UUID, Product> PRODUCTS = new HashMap<>();

    static {
        Product tv = new Product("TV", BigDecimal.valueOf(2799));
        Product smartphone = new Product("Smartphone", BigDecimal.valueOf(2399));
        Product notebook = new Product("Notebook", BigDecimal.valueOf(3999));

        PRODUCTS.put(tv.getId(), tv);
        PRODUCTS.put(smartphone.getId(), smartphone);
        PRODUCTS.put(notebook.getId(), notebook);
    }

    @GET
    public List<Product> findAll() {
        return List.copyOf(PRODUCTS.values());
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") UUID id) {
        Product product = PRODUCTS.get(id);

        if (product == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(product).build();
    }

}
