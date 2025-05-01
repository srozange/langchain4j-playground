package org.theglump.coffee.mcp;

import org.theglump.coffee.mcp.entity.BeanRatingRequest;
import org.theglump.coffee.mcp.entity.CoffeeBean;
import org.theglump.coffee.mcp.entity.CoffeeBeanRating;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.UUID;

@RegisterRestClient(configKey = "favorite-coffee")
@Path("beans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FavoriteCoffeeClient {

    @GET
    List<CoffeeBean> beans(@QueryParam("flavor") @DefaultValue("") String flavor);

    @GET
    @Path("rated")
    List<CoffeeBeanRating> ratedBeans();

    @GET
    @Path("recommended")
    List<CoffeeBean> recommendedBeans();

    @GET
    @Path("untested")
    List<CoffeeBean> untestedCoffeeBeans();

    @PUT
    @Path("{uuid}/ratings")
    void rateBean(@PathParam("uuid") UUID uuid, BeanRatingRequest entity);

}
