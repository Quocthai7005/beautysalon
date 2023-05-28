package com.doctor.spa.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id")
	private Long roleId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "user_role")
	private String userRole;


    public Long getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
 

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
