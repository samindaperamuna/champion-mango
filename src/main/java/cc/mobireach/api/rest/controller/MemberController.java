package cc.mobireach.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.type.PhoneNumber;

import cc.mobireach.api.data.MemberRespository;
import cc.mobireach.api.data.SubscriptionRepository;
import cc.mobireach.api.data.UserRepository;
import cc.mobireach.api.data.UserTypeRepository;
import cc.mobireach.api.model.Member;
import cc.mobireach.api.model.Subscription;
import cc.mobireach.api.model.User;
import cc.mobireach.api.rest.beans.Message;
import cc.mobireach.api.rest.beans.NewMember;
import cc.mobireach.api.security.SecurityUser;
import cc.mobireach.api.util.ImageUtil;
import cc.mobireach.api.util.NumUtils;
import cc.mobireach.api.util.TwilioUtil;

@RestController
public class MemberController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	MemberRespository memberRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@ResponseBody
	@RequestMapping(path = "/verify", method = RequestMethod.POST)
	public Message verifyUser(@RequestBody Message response) {
		// Dummy code
		String code = NumUtils.generatePin();
		TwilioUtil.sendSMSMessage(new PhoneNumber(response.getMessage()), code);
		
		return new Message(code);
	}

	@ResponseBody
	@RequestMapping("/subscriptions")
	public Object getSubscriptions() {
		for (Member member : SecurityUser.getLoggedInUser(userRepository).getMembers()) {
			List<Subscription> subscriptions = subscriptionRepository.findByMember(member);

			if (!subscriptions.isEmpty()) {
				return subscriptions;
			}
		}

		return new Message("Member has no subscriptions.");
	}

	@ResponseBody
	@RequestMapping("/profile")
	public Object getUserProfile() {
		Member member = null;

		for (Member tempMember : SecurityUser.getLoggedInUser(userRepository).getMembers()) {
			member = tempMember;
		}

		if (member != null) {
			return member;
		} else {
			return new Message("Error retrieving member profile.");
		}
	}

	@ResponseBody
	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	public Message createUserProfile(@RequestBody NewMember newMember) {
		User user = new User();
		Member member = new Member();

		try {
			user.setUsername(newMember.getUsername());
			user.setPassword(passwordEncoder.encode(newMember.getPassword()));
			user.setUserType(userTypeRepository.findByUserTypeId(1));
			user.setName(newMember.getName());
			user.setPhone(newMember.getPhone());

			if (!newMember.getEmail().isEmpty())
				user.setEmail(newMember.getEmail());

			if (newMember.getImage() != null)
				user.setImage(ImageUtil.getByteImage(newMember.getImage()));
			else
				user.setImage(ImageUtil.getByteImage(ImageUtil.getGenericUserImageBase64()));

			member.setUser(user);
			member.setGender(newMember.getGender());

			if (newMember.getAge() != 0)
				member.setAge(newMember.getAge());

			memberRepository.save(member);
			return new Message("Member creation successful.");
		} catch (Exception ex) {
			return new Message("Cannot create member. Reason : " + ex.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(path = "/profile", method = RequestMethod.PUT)
	public Message updateUserProfile(@RequestBody NewMember newMember) {
		Member member = null;

		try {
			for (Member tempMember : SecurityUser.getLoggedInUser(userRepository).getMembers()) {
				member = tempMember;
				break;
			}

			if (newMember.getAge() != 0) {
				member.setAge(newMember.getAge());
			}

			if (newMember.getGender() != '\u0000') {
				member.setGender(newMember.getGender());
			}

			if (!newMember.getName().isEmpty()) {
				member.getUser().setName(newMember.getName());
			}

			if (!newMember.getEmail().isEmpty()) {
				member.getUser().setEmail(newMember.getEmail());
			}

			if (!newMember.getPhone().isEmpty()) {
				member.getUser().setPhone(newMember.getPhone());
			}

			if (!newMember.getImage().isEmpty()) {
				member.getUser().setImage(ImageUtil.getByteImage(newMember.getImage()));
			}

			memberRepository.save(member);
			return new Message("Member successfully updated.");
		} catch (Exception ex) {
			return new Message("Error updating member. Reason : " + ex.getMessage());
		}
	}
}
