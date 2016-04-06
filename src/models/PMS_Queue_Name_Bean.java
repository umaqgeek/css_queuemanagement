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
public class PMS_Queue_Name_Bean {
    
    private String queue_type;
    private String queue_name;
    private String queue_description;
    private String user_id;
    private int quota;
    
    public String toString() {
        return "("+getQueue_type()+") "+getQueue_name();
    }

    public String getQueue_type() {
        return queue_type;
    }

    public void setQueue_type(String queue_type) {
        this.queue_type = queue_type;
    }

    public String getQueue_name() {
        return queue_name;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }

    public String getQueue_description() {
        return queue_description;
    }

    public void setQueue_description(String queue_description) {
        this.queue_description = queue_description;
    }
    
    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    
}
