package org.familysearch.joepdp.filesystem;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class FileEndpoint {
  private
  @Context
  UriInfo uriInfo;

  @GET
  @Path("/file{path:.*}")
  public Response getFile(@PathParam("path") String path) throws Exception {
    if(path.equals("") || path.equals("/")){
      return getResponseFromFile(null);
    }
    else {
      return getResponseFromFile(new File(URLDecoder.decode(path, "UTF-8")));
    }
  }

  private Response getResponseFromFile(File file) throws Exception {
    if(file == null) {
      FileResource rootFileResource = createRootFileResource();
      populateFileResourceWithChildren(rootFileResource, File.listRoots());
      Response.ResponseBuilder responseBuilder = Response.ok(rootFileResource);
      return responseBuilder.build();
    }
    else if (file.exists()) {
      if( file.isDirectory()) {
        FileResource fileResource = createFileResourceFromFile(file);
        File parentFile = file.getParentFile();
        if(parentFile != null) {
          fileResource.setParent(createFileResourceFromFile(parentFile));
        }
        else {
          fileResource.setParent(createRootFileResource());
        }
        populateFileResourceWithChildren(fileResource, file.listFiles());
        Response.ResponseBuilder responseBuilder = Response.ok(fileResource);
        return responseBuilder.build();
      }
      else {
        Response.ResponseBuilder responseBuilder = Response.ok(file, MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file));
        return responseBuilder.build();
      }
    }
    else {
      Response.ResponseBuilder responseBuilder = Response.status(404);
      responseBuilder.entity("File not found");
      return responseBuilder.build();
    }
  }

  private FileResource createFileResourceFromFile(File file) throws Exception {
    FileResource fileResource = new FileResource();
    // web servers don't like the "\" character to be escaped
    fileResource.setHref(uriInfo.getBaseUri() + "file/" + URLEncoder.encode(file.getPath(), "UTF-8").replaceAll("%5C", "\\\\"));
    String name = file.getName();
    if(name.length()<1) {
      name = file.getPath();
    }
    fileResource.setName(name);
    return fileResource;
  }

  private FileResource createRootFileResource() {
    FileResource rootFileResource = new FileResource();
    rootFileResource.setHref(uriInfo.getBaseUriBuilder().clone().path("file").build().toASCIIString());
    rootFileResource.setName("ROOT");
    return rootFileResource;
  }

  private void populateFileResourceWithChildren(FileResource fileResource, File[] children) throws Exception {
    for (File file : children) {
      FileResource childFileResource = createFileResourceFromFile(file);
      fileResource.addChild(childFileResource);
    }
  }
}
