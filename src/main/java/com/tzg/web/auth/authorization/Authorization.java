package com.tzg.web.auth.authorization;

import com.tzg.service.support.proto.ProtoBean;

import java.io.Serializable;
import java.util.Date;

public class Authorization extends ProtoBean implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date cdt;

    private Date udt;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
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
        return "Authorization{" + "id=" + id + ", userId=" + userId + ", roleId=" + roleId + ", cdt=" + cdt + ", udt=" + udt + '}';
    }

}
