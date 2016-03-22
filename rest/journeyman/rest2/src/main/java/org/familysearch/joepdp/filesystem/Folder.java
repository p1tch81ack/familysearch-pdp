package org.familysearch.joepdp.filesystem;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * Created by shullja on 3/21/2016.
 */

@XmlRootElement(name = "folder")
public class Folder {
  private List<FolderEntry> folderEntries;

  @XmlElementWrapper(name = "folderEntries")
  @XmlElementRef
  public List<FolderEntry> getFolderEntries() {
    return folderEntries;
  }

  public void setFolderEntries(List<FolderEntry> folderEntries) {
    this.folderEntries = folderEntries;
  }
}
