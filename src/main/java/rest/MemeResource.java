package rest;

import com.google.gson.Gson;
import utils.HttpUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Path("memes")
public class MemeResource {

    @Context
    SecurityContext securityContext;
    Gson gson = new Gson();
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMemes() throws IOException, ExecutionException, InterruptedException {
        String result = gson.toJson(HttpUtils.fetchDataParallel());
        return result;
    }
}

