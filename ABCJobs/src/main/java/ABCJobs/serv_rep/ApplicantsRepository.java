package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.Applicants;
import ABCJobs.bean.Job;

@Repository
public interface ApplicantsRepository extends JpaRepository<Applicants, Long>{
	@Query(value="Select * from applicants where jobID = :keyword",nativeQuery = true)
	public List<Applicants> getByJid(@Param("keyword") Long keyword);
	
	@Query(value="Select * from applicants where userID = :keyword",nativeQuery = true)
	public List<Applicants> getByUid(@Param("keyword") Long keyword);
	
	@Query(value="Select * from applicants where jobID = :jobID AND userID = :userID",nativeQuery = true)
	public Applicants getExisting(@Param("jobID") Long jobId, @Param("userID") Long userID);

	@Query(value="Select * from applicants where userID = :keyword AND status = 'Pending'",nativeQuery = true)
	public List<Applicants> getUserApplications(@Param("keyword") Long keyword);
	
	@Query(value="SELECT * FROM applicants WHERE status = 'Pending'",nativeQuery = true)
	public List<Applicants> findPending();
	
	@Query(value="SELECT * FROM applicants WHERE status = 'Approved'",nativeQuery = true)
	public List<Applicants> findApproved();
	
	@Query(value="SELECT * FROM applicants WHERE status = 'Rejected'",nativeQuery = true)
	public List<Applicants> findRejected();
	
	@Query(value="SELECT * FROM applicants WHERE userID LIKE :keyword ORDER BY applicantID DESC LIMIT 1",nativeQuery = true)
	public List<Applicants> findLatest(@Param("keyword") Long keyword);
	
	@Query(value="SELECT * FROM applicants WHERE userID LIKE :keyword AND status LIKE 'Accepted' ORDER BY applicantID DESC LIMIT 1",nativeQuery = true)
	public Applicants findLatestAccepted(@Param("keyword") Long keyword);
	
}
