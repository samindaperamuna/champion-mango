package cc.mobireach.api.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cc.mobireach.api.data.UserRepository;
import cc.mobireach.api.model.User;

@Service
public class SecurityUser {

	/**
	 * Returns the current logged in user.
	 * @param userRepository
	 * @return
	 */
	public static User getLoggedInUser(UserRepository userRepository) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findByUsername(userDetails.getUsername());
	}
}
