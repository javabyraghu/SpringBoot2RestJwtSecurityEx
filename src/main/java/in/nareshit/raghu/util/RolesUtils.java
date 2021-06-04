package in.nareshit.raghu.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.model.ERole;
import in.nareshit.raghu.model.Role;
import in.nareshit.raghu.repo.RoleRepository;

@Component
public class RolesUtils {

	@Autowired
	private RoleRepository repository;

	public void mapRoles(Set<String> userRoles, Set<Role> dbRoles) {

		if(userRoles == null || userRoles.isEmpty()) {
			Role userRole = repository.findByName(ERole.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error: Role is not found"));
			dbRoles.add(userRole);
		} else {
			userRoles.forEach(role->{
				switch (role) {
				case "admin":
					Role adminRole = repository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(()->new RuntimeException("Error: Role is not found"));
					dbRoles.add(adminRole);
					break;

				case "mod":
					Role modRole = repository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(()->new RuntimeException("Error: Role is not found"));
					dbRoles.add(modRole);
					break;

				default:
					Role userRole = repository.findByName(ERole.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error: Role is not found"));
					dbRoles.add(userRole);
					break;
				}
			});
		}

	}
}
