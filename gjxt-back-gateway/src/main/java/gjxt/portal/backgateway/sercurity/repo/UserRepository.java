package gjxt.portal.backgateway.sercurity.repo;

import gjxt.portal.backgateway.sercurity.domain.auth.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsernameAndPassword(String userName, String password);
	
	User findByUsername(String username);
	
//	User findByEmail(String email);
	
//	User findByPhonenumber(String phone);

	void deleteByUsername(String userName);

    @Modifying
    @Transactional
    @Query("update User u set " +
            "u.username = CASE WHEN :#{#user.userName} IS NULL THEN u.username ELSE :#{#user.username} END ," +
            "u.password =  CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END ," +
            "u.passwordResetDate =  CASE WHEN :#{#user.passwordResetDate} IS NULL THEN u.passwordResetDate ELSE :#{#user.passwordResetDate} END ," +
            "u.status =  CASE WHEN :#{#user.status} IS NULL THEN u.status ELSE :#{#user.status} END " +
            "where u.userId = :#{#user.userId}")
	int updateUserSelective(@Param("user") User user);

    
//    @Modifying
//    @Transactional
//    @Query("update ClientUser u set " +
//            "u.loginname = CASE WHEN :#{#user.nickname} IS NULL THEN u.nickname ELSE :#{#user.nickname} END ," +
//            "u.userName = CASE WHEN :#{#user.userName} IS NULL THEN u.userName ELSE :#{#user.userName} END ," +
//            "u.phone = CASE WHEN :#{#user.phone} IS NULL THEN u.phone ELSE :#{#user.phone} END ," +
//            "u.password =  CASE WHEN :#{#user.password} IS NULL THEN u.password ELSE :#{#user.password} END ," +
//            "u.openId =  CASE WHEN :#{#user.openId} IS NULL THEN u.openId ELSE :#{#user.openId} END ," +
//            "u.avatar =  CASE WHEN :#{#user.avatar} IS NULL THEN u.avatar ELSE :#{#user.avatar} END ," +
//            "u.passwordResetDate =  CASE WHEN :#{#user.passwordResetDate} IS NULL THEN u.passwordResetDate ELSE :#{#user.passwordResetDate} END ," +
//            "u.status =  CASE WHEN :#{#user.status} IS NULL THEN u.status ELSE :#{#user.status} END " +
//            "where u.phone = :#{#user.phone} ")
//	int updateByPhone(@Param("user")User user);

}
