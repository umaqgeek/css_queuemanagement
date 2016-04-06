/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author umarmukhtar
 */
public class PMS_Queue_Type_Bean {
    
    private String queue_type;
    private String queue_type_name;
    private String status;
    
    public String toString() {
        return "("+getQueue_type()+") "+getQueue_type_name();
    }

    public String getQueue_type() {
        return queue_type;
    }

    public void setQueue_type(String queue_type) {
        this.queue_type = queue_type;
    }

    public String getQueue_type_name() {
        return queue_type_name;
    }

    public void setQueue_type_name(String queue_type_name) {
        this.queue_type_name = queue_type_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
