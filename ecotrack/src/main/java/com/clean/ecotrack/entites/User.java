package com.clean.ecotrack.entites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false, length = 26)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	
	private int age;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference("user-request")
	private List<RecycleRequest> recycleRequests;
	
	@ManyToOne
	@JsonManagedReference
	private Role role;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference("user-enrollments")
	private List<Enrollments> enrollments=new ArrayList<Enrollments>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.getAppRole().toString()));
		
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }

	
	@Override
	public  boolean isAccountNonExpired() {
		return true;
	}

	
	@Override
	public  boolean isAccountNonLocked() {
		return true;
	}

	
	@Override
	public  boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public  boolean isEnabled() {
		return true;
	}
}



