package cc.mobireach.api.util;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class TwilioUtil {

	public static final String ACCOUNT_SID = "ACfbe8627736e2c18d8a6e5eeeca0871af";
	public static final String AUTH_TOKEN = "f49dcf07f98402723b3225be7bc42522";
	public static final String SERVICE_NUMBER_STRING = "+16239001371";
	public static final PhoneNumber SERVICE_NUMBER;

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		SERVICE_NUMBER = new PhoneNumber(SERVICE_NUMBER_STRING);
	}

	public static void sendSMSMessage(PhoneNumber number, String msg) {
		try {
			Message.creator(number, SERVICE_NUMBER, msg).create();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
