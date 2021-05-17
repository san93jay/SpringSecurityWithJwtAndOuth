/**
 * 
 */
package com.jwt.SpringSecurityWithJwtAndOuth.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.jwt.SpringSecurityWithJwtAndOuth.enums.RoleName;


import lombok.Data;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
@Data
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Enumerated(EnumType.STRING)
	private RoleName name;	
}