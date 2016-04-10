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
public class Adm_user_bean {
    
    private String user_id;
    private String user_name;
    private String health_facility_code;
    private String discipline;
    private String subdiscipline;
    
    public String toString() {
        return "("+user_id+") "+user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHealth_facility_code() {
        return health_facility_code;
    }

    public void setHealth_facility_code(String health_facility_code) {
        this.health_facility_code = health_facility_code;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getSubdiscipline() {
        return subdiscipline;
    }

    public void setSubdiscipline(String subdiscipline) {
        this.subdiscipline = subdiscipline;
    }
}
