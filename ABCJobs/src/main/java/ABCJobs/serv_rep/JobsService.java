package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ABCJobs.bean.Job;
import ABCJobs.bean.Threads;

@Service
@Transactional
public class JobsService {
	@Autowired
	JobsRepository Jrepo;
	
	public void save(Job j) {
		Jrepo.save(j);
	}
	
	 public Job get(Long id) {
		 return Jrepo.findById(id).get();
	 }
	
	public List<Job> listAll(){
		return (List<Job>) Jrepo.findAll();
	}
	
	public List<Job> adminList(){
		return (List<Job>) Jrepo.adminList();
	}
	
	public List<Job> findOpen() {
		return Jrepo.findOpen();
	}

	
	public List<Job> findClose() {
		return Jrepo.findClose();
	}
}
