package org.familysearch.joepdp.filesystem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * Created by shullja on 3/21/2016.
 */

@SuppressWarnings("WeakerAccess")
@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileResource {
  @XmlAttribute
  private String href;
  @XmlAttribute
  private String contentType;
  @XmlAttribute
  private boolean isDirectory;
  @XmlElement
  private String name;
  @XmlElement
  private FileResource parent;
  @XmlElementWrapper(name = "children")
  @XmlElementRef
  private List<FileResource> children;

  @SuppressWarnings("unused")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }


  @SuppressWarnings("unused")
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @SuppressWarnings("unused")
  public String getName() {
    return name;
  }

  @SuppressWarnings("unused")
  public boolean getIsDirectory() {
    return isDirectory;
  }

  public void setIsDirectory(boolean directory) {
    isDirectory = directory;
  }

  public void setName(String name) {
    this.name = name;
  }

  @SuppressWarnings("unused")
  public List<FileResource> getChildren() {
    return children;
  }

  public void addChild(FileResource child) {
    if(children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }
  @SuppressWarnings("unused")
  public void setChildren(List<FileResource> children) {
    this.children = children;
  }

  @SuppressWarnings("unused")
  public FileResource getParent() {
    return parent;
  }

  public void setParent(FileResource parent) {
    this.parent = parent;
  }
}
