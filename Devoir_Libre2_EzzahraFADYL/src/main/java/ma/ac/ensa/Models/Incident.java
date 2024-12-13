package ma.ac.ensa.Models;

import java.sql.Timestamp;

public class Incident {
    int reference;
    Timestamp time;
    String status ;

    public Incident(int reference, Timestamp time, String status) {
        this.reference = reference;
        this.time =time;
        this.status =status;
    }

    public Incident(int reference) {
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public Timestamp getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}
