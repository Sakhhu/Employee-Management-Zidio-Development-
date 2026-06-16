package com.EMPMANAGE.Security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.EMPMANAGE.Enum.Permission;
import com.EMPMANAGE.Enum.Role;

public class RoleBasedPermission {

    public static Map<Role, Set<Permission>> getRoleBasePermission() {

        Map<Role, Set<Permission>> permissionsMap = new HashMap<>();

        permissionsMap.put(
                Role.ADMIN,
                new HashSet<>(Arrays.asList(
                        Permission.CREATE_EMP,
                        Permission.UPDATE_EMP,
                        Permission.VIEW_EMP,
                        Permission.DELETE_EMP,
                        Permission.RUN_PAYROLL,
                        Permission.VIEW_PAYROLL,
                        Permission.APPROVE_LEAVE,
                        Permission.VIEW_ANALYTICS
                ))
        );

        permissionsMap.put(
                Role.HR,
                new HashSet<>(Arrays.asList(
                        Permission.CREATE_EMP,
                        Permission.UPDATE_EMP,
                        Permission.VIEW_EMP,
                        Permission.VIEW_PAYROLL,
                        Permission.APPROVE_LEAVE
                ))
        );

        permissionsMap.put(
                Role.EMPLOYEE,
                new HashSet<>(Arrays.asList(
                        Permission.APPLY_LEAVE
                ))
        );

        return permissionsMap;
    }
}