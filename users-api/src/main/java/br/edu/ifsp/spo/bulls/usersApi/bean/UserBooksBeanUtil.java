package br.edu.ifsp.spo.bulls.usersApi.bean;

import br.edu.ifsp.spo.bulls.usersApi.domain.Profile;
import br.edu.ifsp.spo.bulls.usersApi.domain.UserBooks;
import br.edu.ifsp.spo.bulls.usersApi.dto.UserBooksTO;
import br.edu.ifsp.spo.bulls.usersApi.exception.ResourceNotFoundException;
import br.edu.ifsp.spo.bulls.usersApi.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserBooksBeanUtil {


    @Autowired
    private ProfileRepository profileRep;

    public UserBooksTO toDto(UserBooks userBooks) {
        UserBooksTO userBooksTO = new UserBooksTO();
        try{
            BeanUtils.copyProperties(userBooks, userBooksTO);
        }catch(Exception e) {
            e.printStackTrace();
        }
        userBooksTO.setStatus(userBooks.getStatus());
        return userBooksTO;
    }

    public UserBooks toDomain(UserBooksTO dto) {
        UserBooks userBooks = new UserBooks();
        try{
            BeanUtils.copyProperties(dto, userBooks);
        }catch(Exception e) {
            e.printStackTrace();
        }
        userBooks.setStatus(dto.getStatus());
        Profile profile = profileRep.findById(dto.getProfileId()).orElseThrow( () -> new ResourceNotFoundException("Profile not found"));
        userBooks.setProfile(profile);
        return userBooks;
    }

    Set<UserBooksTO> toDtoSet(Set<UserBooks> userBooks) {
        return userBooks.parallelStream().map(this::toDto).collect(Collectors.toSet());
    }
}
