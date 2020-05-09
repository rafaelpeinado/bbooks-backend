package br.edu.ifsp.spo.bulls.usersApi.bean;

import br.edu.ifsp.spo.bulls.usersApi.dto.UserTO;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.BeanUtils;
import br.edu.ifsp.spo.bulls.usersApi.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserBeanUtil {

	public User toUser(UserTO userTO) {
		User user = new User();
		try {
			BeanUtils.copyProperties(userTO, user);
		}catch(Exception e) {
			
		}
		return user;
	}
	
	public UserTO toUserTO(User user) {
		UserTO userTO = new UserTO();
		try{
			BeanUtils.copyProperties(user, userTO);
		}catch(Exception e) {
			
		}
		return userTO;
	}
	public HashSet<UserTO> toUserTO(HashSet<User> users){
		
		HashSet<UserTO> usersTO = null;

	    for (User user: users ) {
	        usersTO.add(toUserTO(user));
	     }
		
		return usersTO;
	}
}
