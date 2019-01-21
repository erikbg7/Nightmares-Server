package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/authentication", description = "Endpoint to Auth Service")
@Path("/authentication")
public class AuthService {
    private GameManager gm;

    public AuthService() {
        this.gm = GameManagerImpl.getInstance();
        /*if (gm.sizeUsers()==0) {
            this.gm.addUser("Tfue", "123", 278);
            this.gm.addUser("Ninja", "sadadsadds", 199);
            this.gm.addUser("Lolito", "lolito", 555);
        }*/
    }


    @POST
    @ApiOperation(value = "Log into the system", notes = "Fill with your username and password.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Wrong user or pass")
    })
    @Path("/LogIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(Credentials auth) {
        try{
            boolean ok = this.gm.logIn(auth.getName(), auth.getPassword());
            System.out.println(auth.getName() + auth.getPassword());
            if (!ok) return Response.status(400).build();
            else{
                return Response.status(200).entity(auth).build();
            //                return Response.status(200).entity("0").build();
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }



    @POST
    @ApiOperation(value = "Create a new User", notes = "Introduce username and password.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Wrong user or pass")
    })
    @Path("/SignUp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignUp(Credentials auth) {
        try{
            this.gm.signUp(auth.getName(), auth.getPassword());
            return Response.status(200).entity(auth).build();
        }catch (Exception e){
            e.printStackTrace();;
            return Response.status(400).build();
        } /*catch (NameAlreadyInUseException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }*/
    }

    /*@POST
    @ApiOperation(value = "Create a new User", notes = "Introduce username and password.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
    })

    @Path("/SignUp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) throws NameAlreadyInUseException {
        //this.gm.addUser(user);
        try{
            this.gm.signUp(user.getUsername(), user.getPassword());
            return Response.status(201).entity(user).build();
        }catch (Exception e){
            throw new NameAlreadyInUseException();
        }
    }*/




/*

    @GET
    @ApiOperation(value = "Login v2", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Wrong user or pass")
    })
    @Path("/LoginV2/{username},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLoginV2(@PathParam("username") String username, @PathParam("password") String password) {
        boolean ok = this.gm.checkLogin(username, password);
        if (!ok) return Response.status(400).build();
        else{
            return Response.status(200).build();
        }
    }


    @GET
    @Path("/Login/{username},{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkLogin(@PathParam("username") String username, @PathParam("password") String password) {
        boolean ok = this.gm.checkLogin(username, password);
        if (!ok) return "U are not welcomed";
        else{
            return "Hello sir!";
        }
    }*/
}
