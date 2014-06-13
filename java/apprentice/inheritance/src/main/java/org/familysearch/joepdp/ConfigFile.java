package org.familysearch.joepdp;

import java.io.IOException;

public class ConfigFile extends SimpleTextFile implements Commentable {
    public ConfigFile(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void appendComment(String comment) throws IOException {
        append("# " + comment + "\n");
    }
}
