package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value="Select * from userlogin where email = :keyword",nativeQuery = true)
	public User getByEmail(@Param("keyword") String keyword);
	
	@Query(value="Select * from userlogin where userPID = :keyword",nativeQuery = true)
	public User getByPid(@Param("keyword") Long keyword);
	
	@Query(value="SELECT * FROM abcjobs.userlogin LIMIT 12321341234 OFFSET 1",nativeQuery = true)
	public List<User> adminList();
	
	
	
}
