package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/items", description = "Endpoint to Item Service")
@Path("/items")
public class ItemService {
    private GameManager gm;

    public ItemService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.sizeItems()==0) {
            this.gm.addItem(0, "AK-47", 1, 10, 10, 0);
            this.gm.addItem(1, "P90", 1, 17, 15, 0);
            this.gm.addItem(2, "SCAR", 1, 30, 9, 0);
        }
    }

    @GET
    @ApiOperation(value = "get all Items", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.gm.findAllItems();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        Item t = this.gm.getItem(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") int id) {
        Item t = this.gm.getItem(id);
        if (t == null) return Response.status(404).build();
        else this.gm.deleteItem(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/")
    public Response updateItem(Item item) {
        Item t = this.gm.getItem(item.getId());
        if (t == null) return Response.status(404).build();
        else this.gm.updateItem(t);
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Item.class),
    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newItem(Item item   ) {
        this.gm.addItem(item);
        return Response.status(201).entity(item).build();
    }

}
