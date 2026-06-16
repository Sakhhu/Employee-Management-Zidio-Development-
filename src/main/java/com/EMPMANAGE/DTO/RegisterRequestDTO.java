package com.EMPMANAGE.DTO;

import org.jspecify.annotations.Nullable;

import com.EMPMANAGE.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String userOfficialEmail;
    private String password;
    private Role role;
	public Role getRole() {
		return null;
	}
	public String getUserOfficialEmail() {
		return null;
	}
	public @Nullable CharSequence getPassword() {
		return null;
	}
	public String getUsername() {
		return null;
	}
}