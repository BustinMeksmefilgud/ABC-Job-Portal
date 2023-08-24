package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.Threads;

@Repository
public interface ThreadsRepository extends JpaRepository<Threads, Long> {
	@Query(value="SELECT * FROM threads WHERE userID LIKE :keyword ORDER BY threadsID DESC",nativeQuery = true)
	public List<Threads> findSpecific(@Param("keyword") Long keyword);
	
	@Query(value="SELECT * FROM threads ORDER BY threadsID DESC",nativeQuery = true)
	public List<Threads> dashView();
}
