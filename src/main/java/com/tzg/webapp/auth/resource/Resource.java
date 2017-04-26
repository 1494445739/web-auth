package com.tzg.webapp.auth.resource;

import com.tzg.service.support.proto.ProtoBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Resource extends ProtoBean implements Serializable {

    private Integer id;

    private Integer pid;

    private String name;

    private char category;

    private Integer seq;

    private String uri;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid( Integer pid ) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory( char category ) {
        this.category = category;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq( Integer seq ) {
        this.seq = seq;
    }

    public String getUri() {
        return uri;
    }

    public void setUri( String uri ) {
        this.uri = uri;
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
        return "Resource{" + "id=" + id + ", pid=" + pid + ", name='" + name + '\'' + ", category=" + category + ", seq=" + seq + ", uri='" + uri + '\'' + ", cdt=" + cdt + ", udt=" + udt + '}';
    }

}
