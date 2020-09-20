
package br.edu.ifsp.spo.bulls.usersApi.service;

import java.util.HashSet;
import br.edu.ifsp.spo.bulls.usersApi.bean.UserBeanUtil;
import br.edu.ifsp.spo.bulls.usersApi.domain.Profile;
import br.edu.ifsp.spo.bulls.usersApi.domain.User;
import br.edu.ifsp.spo.bulls.usersApi.dto.CadastroUserTO;
import br.edu.ifsp.spo.bulls.usersApi.dto.UserTO;
import br.edu.ifsp.spo.bulls.usersApi.enums.EmailSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifsp.spo.bulls.usersApi.repository.UserRepository;
import br.edu.ifsp.spo.bulls.usersApi.service.impl.EmailServiceImpl;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceBadRequestException;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceConflictException;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceNotFoundException;

@Service
public class UserService{

	@Autowired
	private UserRepository rep;
	
	@Autowired
	EmailServiceImpl email;
	
	@Autowired
	private UserBeanUtil beanUtil;
	
	@Autowired
	private ProfileService profileService;
	
	public UserTO save(CadastroUserTO cadastroUserTO) throws Exception {
        
		User user = beanUtil.toUser(cadastroUserTO);
		user.setUserName(user.getUserName().toLowerCase());

		Profile profile = new Profile (cadastroUserTO.getName(), cadastroUserTO.getLastName(), user);
		
		validationUserNameIsUnique(user);
		validationEmailIsUnique(user);
		validationPassword(cadastroUserTO.getPassword());
		
		User retorno = rep.save(user);
		profileService.save(profile);

		email.
			getInstance()
			.withUrls("https://bbooks-ifsp.herokuapp.com/confirm")
			.withTo(retorno.getEmail())
			.withContent(" Bem Vindo " + retorno.getUserName())
			.withSubject(EmailSubject.VERIFY_EMAIL.name())
			.send();

		return beanUtil.toUserTO(retorno);
	}

	private void validationPassword(String senha) {
		if(senha.isEmpty())
			throw new ResourceBadRequestException("Senha nao deve estar vazio");
		
	}
	
	private void validationUserNameIsUnique(User entity) throws Exception {
		if (rep.existsByUserName(entity.getUserName())) 
			throw new ResourceConflictException("UserName ja esta sendo usado "  + entity.getUserName());
	}
	
	private void validationEmailIsUnique(User entity) throws Exception {
		Optional<User> user = rep.findByEmail(entity.getEmail());
		if ((user.isPresent()) && (!user.get().getUserName().equals(entity.getUserName())) )
			throw new ResourceConflictException("Email ja esta sendo usado " + entity.getEmail());
	}

	private void validationEmailIsUnique(String email, User entity) throws Exception {
		Optional<User> user = rep.findByEmail(email);
		if ((user.isPresent()) && (!user.get().getUserName().equals(entity.getUserName())) )
			throw new ResourceConflictException("Email ja esta sendo usado " + entity.getEmail());
	}

	public UserTO getById(UUID id) {
		User user = rep.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found"));
		return beanUtil.toUserTO(user);
	}

	public UserTO getByEmail(String email) {
		User user = rep.findByEmail(email).orElseThrow( () -> new ResourceNotFoundException("User not found"));
		return beanUtil.toUserTO(user);
	}

	public void delete(UUID id) {
			
		User user = rep.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found"));
		profileService.deleteByUser(user);
		rep.deleteById(id);
					
	}

	public UserTO update(CadastroUserTO entity) throws Exception {
		if(entity.getId() == null)
			throw new ResourceNotFoundException("User not found");

		User user = beanUtil.toUser(entity);

		validationEmailIsUnique(entity.getEmail(), user);
		validationPassword(entity.getPassword());
		
		return beanUtil.toUserTO(rep.findById(user.getId()).map( user1 -> {
			user1.setEmail(user.getEmail());
			user1.setUserName(user.getUserName());
			return rep.save(user);
		}).orElseThrow( () -> new ResourceNotFoundException("User not found")));
		
	}

	public HashSet<UserTO> getAll() {
		HashSet<User> users = rep.findAll();
		
		return beanUtil.toUserTO(users);
	}

	public User getByToken(String token){
		return rep.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("Autenticação não encontrada."));
	}
	 
   	public Optional<org.springframework.security.core.userdetails.User> findByToken(String token) {
        Optional<User> optionalUser = rep.findByToken(token);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            org.springframework.security.core.userdetails.User userFromSpringSecurity = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(userFromSpringSecurity);
        }
        return  Optional.empty();
    }

	public UserTO getByUserName(String username) {
		return beanUtil.toUserTO(rep.findByUserName(username));
	}

}

