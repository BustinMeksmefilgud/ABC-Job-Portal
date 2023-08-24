package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import ABCJobs.bean.Applicants;


@Service
@Transactional
public class ApplicantsService {

	@Autowired
	ApplicantsRepository repo;
	
	public void save(Applicants a) {
		repo.save(a);
	}
	public List<Applicants> listAll() {
		 return (List<Applicants>) repo.findAll();
	 }
	
	public Applicants get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public List<Applicants> getAllByJid(Long id){
		return repo.getByJid(id);
	}
	
	public List<Applicants> getAllByUid(Long id){
		return repo.getByUid(id);
	}
	
	public List<Applicants> getPending(){
		return repo.findPending();
	}
	
	public List<Applicants> getApproved(){
		return repo.findPending();
	}
	
	public List<Applicants> getRejected(){
		return repo.findRejected();
	}
	
	public List<Applicants> getLatest(Long id) {
		return repo.findLatest(id);
	}
	
	public List<Applicants> getPendingApplications(Long id){
		return repo.getUserApplications(id);
	}
	
	public Applicants getExisting(Long jid, Long uid) {
		return repo.getExisting(jid, uid);
	}
	
	public Applicants getLatestAccepted(Long uid) {
		return repo.findLatestAccepted(uid);
	}
}
