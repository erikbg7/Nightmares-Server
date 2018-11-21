package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;

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
    @ApiOperation(value = "get Users by total score", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/byScore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usersByScore() {

        List<User> usersByScore = this.gm.usersOrderedByScore();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(usersByScore) {};
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
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
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

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        this.gm.addUser(user);
        return Response.status(201).entity(user).build();
    }

    @GET
    @Path("/Login/{username},{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkLogin(@PathParam("username") String username, @PathParam("password") String password) {
        boolean ok = this.gm.checkLogin(username, password);
        if (!ok) return "Ur not welcomed";
        else{
            return "Hello sir!";
        }
    }

}

