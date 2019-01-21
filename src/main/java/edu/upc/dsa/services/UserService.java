package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.NameAlreadyInUseException;
import edu.upc.dsa.models.Score;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UserService {
    private GameManager gm;

    public UserService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.sizeUsers()==0) {
            this.gm.addUser("Tfue", "123", 278);
            this.gm.addUser("Ninja", "sadadsadds", 199);
            this.gm.addUser("Lolito", "lolito", 555);
        }
    }

    @GET
    @ApiOperation(value = "get Users by total score", notes = "Name and total score of each user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Score.class, responseContainer="List"),
    })
    @Path("/byScore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersByScore() {

        List<Score> usersByScore = this.gm.usersOrderedByScore();
        //List<Score> scores = new ArrayList<>();
        /*for(User u: usersByScore){
            scores.add(new Score(u.getUsername(), u.getScore()));
        }*/
        GenericEntity<List<Score>> entity = new GenericEntity<List<Score>>(usersByScore) {};
        return Response.status(201).entity(entity).build()  ;
    }


    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.gm.findAllUsers();
        System.out.println(users.size());
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User u = this.gm.getUser(id);
        if (u == null) return Response.status(404).header("Access-Control-Allow-Origin", "*").build();
        else  return Response.status(201).entity(u).header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User u = this.gm.getUser(id);
        if (u == null) return Response.status(404).build();
        else this.gm.deleteUser(id);
        return Response.status(201).build();
    }



}

