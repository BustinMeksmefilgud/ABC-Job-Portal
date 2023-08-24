package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	@Query(value="Select userPID from userprofile order by userPID desc limit 1",nativeQuery = true)
	public int getLatestId();
	@Query(value="SELECT * FROM userprofile WHERE firstname LIKE :keyword OR lastname LIKE :keyword OR country LIKE :keyword",nativeQuery = true)
	public List<UserProfile> search(@Param("keyword") String keyword);
	@Query(value="SELECT userPID FROM userprofile WHERE firstname LIKE :keyword OR lastname LIKE :keyword OR country LIKE :keyword",nativeQuery = true)
	public String searchID(@Param("keyword") String keyword);
	@Query(value="SELECT * FROM userprofile WHERE firstname LIKE :keyword",nativeQuery = true)
	public UserProfile getByName(@Param("keyword") String keyword);
}
