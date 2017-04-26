package com.tzg.webapp.auth.permission;

import com.tzg.service.support.proto.ProtoBean;

import java.io.Serializable;
import java.util.Date;

public class Permission extends ProtoBean implements Serializable {

    private Integer id;

    private Integer resId;

    private Integer roleId;

    private Date cdt;

    private Date udt;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId( Integer resId ) {
        this.resId = resId;
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
        return "Permission{" + "id=" + id + ", resId=" + resId + ", roleId=" + roleId + ", cdt=" + cdt + ", udt=" + udt + '}';
    }

}
