package ABCJobs.serv_rep;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.Job;


@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
	@Query(value="SELECT * FROM jobs WHERE status = 'OPEN'",nativeQuery = true)
	public List<Job> findOpen();
	
	@Query(value="SELECT * FROM jobs WHERE status = 'CLOSE'",nativeQuery = true)
	public List<Job> findClose();
	
	@Query(value="SELECT * FROM jobs LIMIT 12321341234 OFFSET 1",nativeQuery = true)
	public List<Job> adminList();
}
