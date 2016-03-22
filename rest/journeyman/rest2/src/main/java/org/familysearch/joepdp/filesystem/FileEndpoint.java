package org.familysearch.joepdp.filesystem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/file/{path:.*}")
public class FileEndpoint {
    @GET
    public Response getIt(@PathParam("path") String path) {
        java.io.File file = new java.io.File(path);
        if(file.exists() && file.isFile()){
            Response.ResponseBuilder responseBuilder = Response.ok(file);
            return responseBuilder.build();
        } else {
            Response.ResponseBuilder responseBuilder = Response.status(404);
            responseBuilder.entity("File not found");
            return responseBuilder.build();
        }
    }
}
