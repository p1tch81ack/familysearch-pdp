package org.familysearch.joepdp.filesystem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.sun.xml.txw2.annotation.*;

/**
 *
 * Created by shullja on 3/21/2016.
 */

@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileResource {
  @XmlAttribute
  private String href;
  @XmlElement
  private String name;
  @XmlElement
  private FileResource parent;
  @XmlElementWrapper(name = "children")
  @XmlElementRef
  private List<FileResource> children;

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<FileResource> getChildren() {
    return children;
  }

  public void addChild(FileResource child) {
    if(children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }
  public void setChildren(List<FileResource> children) {
    this.children = children;
  }

  public FileResource getParent() {
    return parent;
  }

  public void setParent(FileResource parent) {
    this.parent = parent;
  }
}
