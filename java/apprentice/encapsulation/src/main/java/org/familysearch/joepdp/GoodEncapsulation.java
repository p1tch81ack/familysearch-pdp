package org.familysearch.joepdp;

import java.util.Date;

//Good encapsulation is a class that happens to hold a date.
//In the real world it would probably do more than that.
//It is meant to be immutable - hence the lack of a setter for date.
//Unfortunately, date is mutable.
//This is dealt with by calling clone in the constructor and getter.

public class GoodEncapsulation {
    private Date date;

    public GoodEncapsulation(Date date){
        this.date = (Date)(date.clone());   // By cloning the date we protect the immutability of this class.
                                            // Its state won't change even if the passed in date changes after construction.
    }

    public Date getDate() {
        return (Date)(date.clone());    // By returning a cloned date to caller, we are preventing them from modifying
                                        // the member date. thus preserving immutability.
    }
}
