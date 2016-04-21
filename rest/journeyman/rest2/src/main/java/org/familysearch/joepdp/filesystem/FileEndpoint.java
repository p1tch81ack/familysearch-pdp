package org.familysearch.joepdp.filesystem;

import java.io.File;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
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
    FileResource fileResource=null;
    int status;
    if(file == null) {
      fileResource = createRootFileResource();
      populateFileResourceWithChildren(fileResource, File.listRoots());
    }
    else if (file.exists()) {
      if(file.isDirectory()) {
        fileResource = createFileResourceFromFile(file);
        File parentFile = file.getParentFile();
        if(parentFile != null) {
          fileResource.setParent(createFileResourceFromFile(parentFile));
        }
        else {
          fileResource.setParent(createRootFileResource());
        }
        File[] children = file.listFiles();
        if(children!=null) {
          populateFileResourceWithChildren(fileResource, children);
        }
      }
      else {
        String contentType = Files.probeContentType(file.toPath());
        Response.ResponseBuilder responseBuilder = Response.ok();
        if(contentType != null) {
          responseBuilder = responseBuilder.header(CONTENT_TYPE, contentType);
        }
        return responseBuilder
            .entity(file)
            .cacheControl(cacheControl)
            .build();
      }
    }
    String valueAsString;
    String contentType;
    if(fileResource != null) {
      status = Response.Status.OK.getStatusCode();
      if(accepts.contains(APPLICATION_XML) && !accepts.contains(APPLICATION_JSON)) {
        contentType = APPLICATION_XML;
        Marshaller marshaller = JAXBContext.newInstance(FileResource.class)
            .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(fileResource, stringWriter);
        valueAsString = stringWriter.toString();      }
      else {
        contentType = APPLICATION_JSON;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        valueAsString = objectMapper.writeValueAsString(fileResource);
      }
    }
    else {
      status = Response.Status.NOT_FOUND.getStatusCode();
      contentType = TEXT_PLAIN;
      valueAsString = "File not found";
    }
    return Response
        .status(status)
        .cacheControl(cacheControl)
        .header(CONTENT_TYPE, contentType)
        .entity(valueAsString)
        .build();
  }

  private FileResource createFileResourceFromFile(File file) throws Exception {
    FileResource fileResource = new FileResource();
    // web servers don't like the "\" character to be escaped
    fileResource.setHref(uriInfo.getBaseUri() + "file/" + URLEncoder.encode(file.getPath(), "UTF-8").replaceAll("%5C", "\\\\"));
    fileResource.setIsDirectory(file.isDirectory());
    fileResource.setContentType(Files.probeContentType(file.toPath()));
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
    rootFileResource.setIsDirectory(true);
    return rootFileResource;
  }

  private void populateFileResourceWithChildren(FileResource fileResource, File[] children) throws Exception {
    for (File file : children) {
      FileResource childFileResource = createFileResourceFromFile(file);
      fileResource.addChild(childFileResource);
    }
  }
}
