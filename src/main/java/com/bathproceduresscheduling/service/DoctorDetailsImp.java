package com.bathproceduresscheduling.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bathproceduresscheduling.entity.DoctorEntity;

public class DoctorDetailsImp implements UserDetails {

	private DoctorEntity doctorEntity;
	
	public DoctorDetailsImp(DoctorEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(doctorEntity.getDocRole()));
//		return authorities;
		return null;
	}

	@Override
	public String getPassword() {
		String pass = doctorEntity.getDocLoginCode();
		String myPass = "{noop}"+pass;
		return myPass;
	}

	@Override
	public String getUsername() {
		return doctorEntity.getDocEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return doctorEntity.getDocActive();
	}

}
