package com.hardik.service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hardik.model.Authorities;
import com.hardik.model.User;


public class MyUserDetails extends Object implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	
	 public MyUserDetails(User user) {
	        this.user = user;
	    }
	
	@SuppressWarnings("unchecked")
	public Collection<? extends GrantedAuthority> getAuthorities() {

//		 return user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
		return	(Collection<? extends GrantedAuthority>) user.getAuthorities().stream().map(new Function<Authorities, Object>() {
			public Object apply(Authorities authority) {
				return new SimpleGrantedAuthority(authority.getAuthority());
			}
		}).collect(Collectors.toList());
	}


//    public int getId() {
//        return user.getId();
//    }
    public String getPassword() {
        return user.getPassword();
    }
    public String getUsername() {
        return user.getUsername();
    }
    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isEnabled() {
        return true;
    }
    public User getUserDetails() {
        return user;
    }

}
