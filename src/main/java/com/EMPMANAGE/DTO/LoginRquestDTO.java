package com.EMPMANAGE.DTO;

import org.jspecify.annotations.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRquestDTO {
    private String userOfficialEmail;
    private String password;
	public @Nullable CharSequence getPassword() {
		
		return null;
	}
	public Object getUserOfficialEmail() {
		
		return null;
	}
}
