package org.familysearch.joepdp.rest2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;

@Path("/folder/{path:.*}")
public class Folder {
    private @Context UriInfo uriInfo;


    @GET
    public Response getIt(@PathParam("path") String path) {
        if(path.equals("")){
            return(getFolderListing(File.listRoots()));
        } else {
            return(getFolderListing(new File(path)));
        }
    }
    private Response getFolderListing(File file){
        if(file.exists() && file.isDirectory()){
          return getFolderListing(file.listFiles());
        } else {
            Response.ResponseBuilder responseBuilder = Response.status(404);
            responseBuilder.entity("File not found");
            return responseBuilder.build();
        }
    }

    private Response getFolderListing(File[] files) {
        StringBuilder out = new StringBuilder("<folder>");
        for(File fileEntry:files){
            if(fileEntry.isDirectory()){
                out.append("<folderentry href=\"");
                out.append(uriInfo.getBaseUri());
                out.append("folder/");
                out.append(fileEntry.getPath());
                out.append("\">");
                out.append(fileEntry.getPath());
                out.append("</folderentry>");
            } else {
                out.append("<fileentry href=\"");
                out.append(uriInfo.getBaseUri());
                out.append("file/");
                out.append(fileEntry.getPath());
                out.append("\">");
                out.append(fileEntry.getPath());
                out.append("</fileentry>");
            }
        }
        out.append("</folder>");
        Response.ResponseBuilder responseBuilder = Response.ok(out.toString());
        responseBuilder.header("ContType", "text/xml");
        return responseBuilder.build();
    }
}
