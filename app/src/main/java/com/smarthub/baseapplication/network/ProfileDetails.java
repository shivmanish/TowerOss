package com.smarthub.baseapplication.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class ProfileDetails {
    @SerializedName("first_name")
    @Expose
    private String first_name;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("active")
    @Expose
    private String active;

    @SerializedName("national")
    @Expose
    private String national;

    @SerializedName("region")
    @Expose
    private List<String> region;

    @SerializedName("state")
    @Expose
    private List<String> state;

    @SerializedName("maintenancepoint")
    @Expose
    private List<String> maintenancepoint;

    @SerializedName("roles")
    @Expose
    private List<String> roles;

    @SerializedName("priviledgename")
    @Expose
    private List<String> priviledgename;

    @SerializedName("department")
    @Expose
    private List<String> department;

    @SerializedName("communicationAddress")
    @Expose
    private HashMap<String,String> communicationAddress;



    public String getId() {
        return id;
    }

    public String getData() {
        return first_name;
    }

    public void setData(String data) {
        this.first_name = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActive() {
        return active;
    }
    public String getNational() {
        return national;
    }
    public List<String> getRegion() {
        return region;
    }
    public List<String> getState() {
        return state;
    }
    public List<String> getMaintenancepoint() {
        return maintenancepoint;
    }
    public String getRoles()
    { String listroles = String.join(", ", roles);
        return listroles;
    }
    public String getDepartment(){
        String listdepartment = String.join(", ", department);
        return listdepartment;}

    public HashMap<String, String> getCommunicationAddress() {
        return communicationAddress;
    }

    public List<String> getPriviledgename() {
        return priviledgename;
    }
}
