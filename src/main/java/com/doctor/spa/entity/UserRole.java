package com.doctor.spa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	private String roleId;
    private String username;
	private String userRole;
 
    @Id
    @Column(name = "role_id", nullable = false)
    public String getRoleId() {
        return roleId;
    }
 
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
 
    @Column(name = "username", nullable = false)
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    
    @Column(name = "user_role")
    public String getUserRole() {
        return userRole;
    }
 
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public enum Role {
    	MIRACLE_ADMIN("MA"),
    	NEWS_ADMIN("NA"),
    	PRODUCT_ADMIN("PA"),
    	CLIENT_ADMIN("CA");
    	

		private Role(String string) {
		
		}
    }
}
