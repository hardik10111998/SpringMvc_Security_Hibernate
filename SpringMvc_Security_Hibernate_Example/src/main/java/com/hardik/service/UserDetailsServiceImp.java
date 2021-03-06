package com.hardik.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hardik.dao.UserDetailsDao;
import com.hardik.model.User;



@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserDetailsDao userDetailsDao;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userDetailsDao.findUserByUsername(username);
//    UserBuilder builder = null;
//    if (user != null) {
//      
//      builder = org.springframework.security.core.userdetails.User.withUsername(username);
//      builder.disabled(!user.isEnabled());
//      builder.password(user.getPassword());
//      String[] authorities = user.getAuthorities()
//          .stream().map(a -> a.getAuthority()).toArray(String[]::new);
//      
////      String authorities = user.getAuthorities().stream()
////    	        .map(GrantedAuthority::getAuthority())
////    	        .collect(Collectors.joining(","));
//
////      String[] authorities = user.getAuthorities()
////            .stream().map(a -> new SimpleGrantedAuthority(user.getAuthorities())).collect(Collector.toList());
//    		  
//      builder.authorities(authorities);
//    } else {
//      throw new UsernameNotFoundException("User not found.");
//    }
//    return builder.build();
    
    if (user == null) {
        throw new UsernameNotFoundException("User not found.");
    }
//    System.out.println("loadUserByUsername() : "+ user.getUsername()+"And Authorities:"+user.getAuthorities());
    return new MyUserDetails(user);
  }
}
