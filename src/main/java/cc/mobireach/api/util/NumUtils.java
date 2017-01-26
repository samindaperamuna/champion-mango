package cc.mobireach.api.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class NumUtils {

	public static String generatePin() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		return String.format("%05d", num);
	}
}
