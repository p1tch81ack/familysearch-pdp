package org.familysearch.joepdp;

import java.util.Date;

// This class is intended to be immutable so it doesn't have a setter for date.
// Unfortunately, java.util.Date is mutable so outside users of this class may make inadvertant modifications...

public class BadEncapsulation {
   private Date date;

    public BadEncapsulation(Date date){
        this.date = date;   // This is bad because the caller of the constructer may modify the passed in date,
                            // thus modifying the state of this class which is supposed to be immutable.
    }

    public Date getDate() {
        return date;    // This is bad because the caller of this method may modify the returned date,
                        // thus modifying the state of this class which is supposed to be immutable.
    }
}
