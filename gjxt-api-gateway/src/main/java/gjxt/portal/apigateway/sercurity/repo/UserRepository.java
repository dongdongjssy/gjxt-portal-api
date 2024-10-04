package gjxt.portal.apigateway.sercurity.repo;

import gjxt.portal.apigateway.sercurity.domain.auth.ClientUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<ClientUser, Long> {

    ClientUser findByPhoneAndPassword(String userName, String password);

    ClientUser findByPhone(String phone);

    ClientUser findByIdNumber(String idNumber);

    ClientUser findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update ClientUser u set " +
            "u.username =  CASE WHEN :#{#user.username} IS NULL THEN u.username ELSE :#{#user.username} END ," +
            "u.password =  CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END ," +
            "u.phone = CASE WHEN :#{#user.phone} IS NULL THEN u.phone ELSE :#{#user.phone} END ," +
            "u.password =  CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END ," +
            "u.status =  CASE WHEN :#{#user.status} IS NULL THEN u.status ELSE :#{#user.status} END " +
            "where u.id=:#{#user.id}")
    int updateUserSelective(@Param("user") ClientUser user);

    @Modifying
    @Transactional
    @Query("update ClientUser u set " +
            "u.username =  CASE WHEN :#{#user.username} IS NULL THEN u.username ELSE :#{#user.username} END ," +
            "u.phone = CASE WHEN :#{#user.phone} IS NULL THEN u.phone ELSE :#{#user.phone} END ," +
            "u.password =  CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END ," +
            "u.status =  CASE WHEN :#{#user.status} IS NULL THEN u.status ELSE :#{#user.status} END " +
            "where u.phone = :#{#user.phone} ")
    int updateByPhone(@Param("user") ClientUser user);
}
