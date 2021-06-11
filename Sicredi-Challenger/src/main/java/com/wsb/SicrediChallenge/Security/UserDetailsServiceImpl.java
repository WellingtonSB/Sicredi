package com.wsb.SicrediChallenge.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wsb.SicrediChallenge.Model.AdminstradorModel;
import com.wsb.SicrediChallenge.Repository.AdminstradorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminstradorRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<AdminstradorModel> user = userRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}

}
