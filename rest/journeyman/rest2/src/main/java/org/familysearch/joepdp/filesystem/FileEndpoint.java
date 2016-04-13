package org.familysearch.joepdp.filesystem;

import java.io.File;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.*;

@Path("/")
public class FileEndpoint {
  private final CacheControl cacheControl = new CacheControl();
  private @Context UriInfo uriInfo;

  public FileEndpoint() {
    cacheControl.setNoCache(true);
  }

  @GET
  @Path("/file{path:.*}")
  public Response getFile(@PathParam("path") String path, @HeaderParam(ACCEPT) String acceptsHeader) throws Exception {
    List<String> accepts = new ArrayList<>();
    if(acceptsHeader!=null) {
      accepts = Arrays.asList(acceptsHeader.split(","));
    }
    if(path.equals("") || path.equals("/")){
      return getResponseFromFile(null, accepts);
    }
    else {
      return getResponseFromFile(new File(URLDecoder.decode(path, "UTF-8")), accepts);
    }
  }

  private Response getResponseFromFile(File file, List<String> accepts) throws Exception {
    FileResource rootFileResource=null;
    Response.ResponseBuilder responseBuilder;
    int status;
    if(file == null) {
      rootFileResource = createRootFileResource();
      populateFileResourceWithChildren(rootFileResource, File.listRoots());
    }
    else if (file.exists()) {
      if(file.isDirectory()) {
        rootFileResource = createFileResourceFromFile(file);
        File parentFile = file.getParentFile();
        if(parentFile != null) {
          rootFileResource.setParent(createFileResourceFromFile(parentFile));
        }
        else {
          rootFileResource.setParent(createRootFileResource());
        }
        populateFileResourceWithChildren(rootFileResource, file.listFiles());
      }
      else {
        String contentType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file);
        return Response.ok(file, contentType).cacheControl(cacheControl).build();
      }
    }
    String valueAsString;
    String contentType;
    if(rootFileResource != null) {
      status = Response.Status.OK.getStatusCode();
      if(accepts.contains(APPLICATION_XML) && !accepts.contains(APPLICATION_JSON)) {
        contentType = APPLICATION_XML;
        Marshaller marshaller = JAXBContext.newInstance(FileResource.class)
            .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(rootFileResource, stringWriter);
        valueAsString = stringWriter.toString();      }
      else {
        contentType = APPLICATION_JSON;
        ObjectMapper om = new ObjectMapper();
        valueAsString = om.writeValueAsString(rootFileResource);
      }
    }
    else {
      status = Response.Status.NOT_FOUND.getStatusCode();
      contentType = TEXT_PLAIN;
      valueAsString = "File not found";
    }
    responseBuilder = Response
        .status(status)
        .cacheControl(cacheControl)
        .header(CONTENT_TYPE, contentType)
        .entity(valueAsString);
    return responseBuilder.build();
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
