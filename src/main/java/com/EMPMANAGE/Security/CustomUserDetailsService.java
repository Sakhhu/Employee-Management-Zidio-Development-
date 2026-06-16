package com.EMPMANAGE.Security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EMPMANAGE.Entity.UserAuthentication;
import com.EMPMANAGE.Enum.Permission;
import com.EMPMANAGE.Repository.UserAuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userOfficialEmail) throws UsernameNotFoundException {

		UserAuthentication user = userRepo.findByUserOfficialEmail(userOfficialEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userOfficialEmail));

		Set<Permission> permissions = RoleBasedPermission.getRoleBasePermission().get(user.getRole());

		List<SimpleGrantedAuthority> authorities = permissions.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());

		return new User(user.getUserOfficialEmail(),user.getPassword(), authorities);
    }
}