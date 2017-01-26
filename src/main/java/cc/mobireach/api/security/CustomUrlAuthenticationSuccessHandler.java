package cc.mobireach.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cc.mobireach.api.data.UserRepository;
import cc.mobireach.api.model.User;

@Configuration
public class CustomUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		updateDatasource(authentication);
	}

	/**
	 * Set the database fields corresponding to the logged in user. LoggedIn
	 * flag is set to true to manage multiple application compatibility.
	 * 
	 * @param authentication
	 */
	protected void updateDatasource(Authentication authentication) {
		String username = authentication.getName();

		User user = userRepository.findByUsername(username);
		user.setLastLoginDate(new Date());
		userRepository.save(user);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}
