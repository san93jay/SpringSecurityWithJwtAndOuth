/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jwt.SpringSecurityWithJwtAndOuth.enums.StatusType;

import lombok.Data;


/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Entity
@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "contact" }) })
@Data
public class User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be Empty")
    @Size(min=3, max = 50)
    private String name;

    @NotBlank(message = "username must not be Empty")
    @Size(min=3, max = 50)
    private String username;

    @NotBlank(message = "password must not be Empty")
    @Size(min=6, max = 100)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
	@Column(name="contact", updatable=true,unique=true)
	private String contact;
	
	@Column(name="address")
	private String address;
	
	@Column(name="country")
    private String country;
	
	@Column(name = "active")
	@Enumerated(EnumType.STRING)
	private StatusType active;
	
	@CreationTimestamp
    @Column(name = "created_time", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@UpdateTimestamp
	@Column(name = "updated_time", updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

    public User() {}

	public User(String name,String username,String password,
			     String contact, String address, String country, StatusType status) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.address = address;
		this.country = country;
		this.active = status;
	}
   
	
}
