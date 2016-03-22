package org.familysearch.joepdp.filesystem;

import javax.xml.bind.annotation.*;

/**
 *
 * Created by shullja on 3/21/2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FolderEntry {
  @XmlAttribute(name = "href")
  private String href;
  @XmlValue
  private String name;

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

}
