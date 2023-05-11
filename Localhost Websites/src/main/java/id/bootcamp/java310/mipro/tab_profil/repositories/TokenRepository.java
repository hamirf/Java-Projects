package id.bootcamp.java310.mipro.tab_profil.repositories;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.bootcamp.java310.mipro.tab_profil.entities.T_Token;

public interface TokenRepository extends JpaRepository<T_Token, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true,
			value = "INSERT INTO T_TOKEN "
					+ "(CREATED_BY, TOKEN, EMAIL, IS_EXPIRED, USED_FOR, CREATED_ON, EXPIRED_ON) "
					+ "VALUES "
					+ "(1, "
					+ ":otp, "
					+ ":email, "
					+ "false, "
					+ ":used_for, "
					+ "NOW(), "
					+ ":expired_on)")
	void insertOTP(String otp, @Param("email") String email, String used_for, Date expired_on);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, 
		value = "UPDATE t_token SET\r\n"
				+ "is_expired = true\r\n"
				+ "WHERE email = :email AND token = :otp")
	void updateIsExpiredOTP(@Param("email") String email, 
			@Param("otp") String otp);
	
	//Query Validasi
	//Cek email sama di tabel user
	@Query(nativeQuery = true, 
			value = "SELECT EXISTS\r\n"
					+ "(SELECT EMAIL\r\n"
					+ "FROM M_USER\r\n"
					+ "WHERE EMAIL = :email)")
	Boolean isEmailExists(@Param("email") String email);

	@Query(nativeQuery = true,
			value = "select exists (select * from (select email,\r\n"
					+ "count(*) as jumlah from t_token "
					+ "where email = :email\r\n"
					+ "group by email) t1\r\n"
					+ "where jumlah >= 10)")
	public Boolean isMaxOTP(@Param("email") String email);
	
	@Query(nativeQuery = true,
			value = "select exists (select token \r\n"
					+ "from t_token where email = :email AND token = :otp\r\n"
					+ "order by created_on desc\r\n"
					+ "limit 1)")
	public Boolean isOTPCorrect(@Param("email") String email, 
			@Param("otp") String otp);

	@Query(nativeQuery = true,
			value = "select exists (select created_on \r\n"
					+ "from t_token \r\n"
					+ "where email = :email \r\n"
					+ "AND token = :otp\r\n"
					+ "AND (now() - expired_on > Interval '0' second OR is_expired = true)\r\n"
					+ "order by created_on desc\r\n"
					+ "limit 1)")
	public Boolean isOTPExpired(@Param("email") String email, 
			@Param("otp") String otp);
	
}
