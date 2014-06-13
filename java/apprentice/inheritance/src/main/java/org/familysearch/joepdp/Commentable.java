package org.familysearch.joepdp;

import java.io.IOException;

public interface Commentable {
    void appendComment(String comment) throws IOException;
}
