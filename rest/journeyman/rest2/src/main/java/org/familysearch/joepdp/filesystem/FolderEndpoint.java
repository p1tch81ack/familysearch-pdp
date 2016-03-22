package org.familysearch.joepdp.filesystem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class FolderEndpoint {
  private
  @Context
  UriInfo uriInfo;

  @GET
  @Path("/folder/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFolderJson() {
      return (getFolderListing(null, null, File.listRoots()));
  }

  @GET
  @Path("/folder/{path:.*}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFolderJson(@PathParam("path") String path) {
    if(path.equals("")){
      return getFolderJson();
    }
    else {
      return (getFolderListing(new File(path)));
    }
  }

  @GET
  @Path("/folder/")
  @Produces(MediaType.APPLICATION_XML)
  public Response getFolderXml() {
    return (getFolderListing(null, null, File.listRoots()));
  }

  @GET
  @Path("/folder/{path:.*}")
  @Produces(MediaType.APPLICATION_XML)
  public Response getFolderXml(@PathParam("path") String path) {
    if(path.equals("")){
      return getFolderXml();
    }
    else {
      return (getFolderListing(new File(path)));
    }
  }

  private Response getFolderListing(File file) {
    if (file.exists() && file.isDirectory()) {
      return getFolderListing(file.getParentFile(), file, file.listFiles());
    }
    else {
      Response.ResponseBuilder responseBuilder = Response.status(404);
      responseBuilder.entity("File not found");
      return responseBuilder.build();
    }
  }

  private Response getFolderListing(File parent, File current, File[] files) {
    List<FolderEntry> folderEntryList = new ArrayList<>();
    if(parent != null) {
      FolderEntry parentFolderEntry = new FolderEntry();
      parentFolderEntry.setHref(uriInfo.getBaseUri() + "folder/" + parent.getPath());
      parentFolderEntry.setName("..");
      folderEntryList.add(parentFolderEntry);
    }
    if(current != null) {
      FolderEntry currentFolderEntry = new FolderEntry();
      currentFolderEntry.setHref(uriInfo.getBaseUri() + "folder/" + current.getPath());
      currentFolderEntry.setName(".");
      folderEntryList.add(currentFolderEntry);
    }
    else {
      FolderEntry rootFolderEntry = new FolderEntry();
      rootFolderEntry.setHref(uriInfo.getBaseUri() + "folder");
      rootFolderEntry.setName(".");
      folderEntryList.add(rootFolderEntry);
    }
    for (File fileEntry : files) {
      FolderEntry folderEntry = new FolderEntry();
      String name = fileEntry.getName();
      if(name.length()<1) {
        name = fileEntry.getPath();
      }
      folderEntry.setName(name);
      if (fileEntry.isDirectory()) {
        folderEntry.setHref(uriInfo.getBaseUri() + "folder/" + fileEntry.getPath());
      }
      else {
        folderEntry.setHref(uriInfo.getBaseUri() + "file/" + fileEntry.getPath());
      }
      folderEntryList.add(folderEntry);
    }
//    FolderEntries folderEntries = new FolderEntries();
//    folderEntries.setFolderList(folderEntryList);
    Folder folder = new Folder();
//    folder.setFolderEntries(folderEntries);
    folder.setFolderEntries(folderEntryList);
    Response.ResponseBuilder responseBuilder = Response.ok(folder);
    return responseBuilder.build();
  }
}
