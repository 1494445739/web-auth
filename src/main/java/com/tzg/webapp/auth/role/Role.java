package com.tzg.webapp.auth.role;

import com.tzg.service.support.proto.ProtoBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Role extends ProtoBean implements Serializable {

    private Integer id;

    private String name;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date cdt;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date udt;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Date getCdt() {
        return cdt;
    }

    public void setCdt( Date cdt ) {
        this.cdt = cdt;
    }

    public Date getUdt() {
        return udt;
    }

    public void setUdt( Date udt ) {
        this.udt = udt;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + ", cdt=" + cdt + ", udt=" + udt + '}';
    }

}
