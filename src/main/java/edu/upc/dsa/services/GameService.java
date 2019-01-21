package edu.upc.dsa.services;

import edu.upc.dsa.*;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/games", description = "Endpoint to Item Service")
@Path("/games")
public class GameService {
    private GameManager gm;

    public GameService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.sizeItems()==0) {
            this.gm.addItem(0, "AK-47", 1, 10, 10, 0);
            this.gm.addItem(1, "P90", 1, 17, 15, 0);
            this.gm.addItem(2, "SCAR", 1, 30, 9, 0);
        }
    }

    @GET
    @ApiOperation(value = "get list of Matches", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
    })
    @Path("/listByScore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Game> games = this.gm.findAllGames();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        return Response.status(201).entity(entity).build()  ;

    }


    @POST
    @ApiOperation(value = "create a new Match register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Game.class),
    })

    @Path("/newMatch")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newItem(Game game) {
        this.gm.addGame(game);
        return Response.status(201).entity(game).build();
    }

}
