package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ABCJobs.bean.Threads;

@Service
@Transactional
public class ThreadsService {

	@Autowired
	ThreadsRepository Trepo;
	
	 public void save(Threads t) {
		 Trepo.save(t);
	 }
	 public Threads get(Long id) {
		 return Trepo.findById(id).get();
	 }
	 public List<Threads> listAll() {
		 return (List<Threads>) Trepo.findAll();
	 }
	 public void delete(Long id) {
		 Trepo.deleteById(id);
	 }
	 public List<Threads> listAllSpecific(Long uid) {
		 return (List<Threads>) Trepo.findSpecific(uid);
	 }
	 
	 public List<Threads> dashboard() {
		 return (List<Threads>) Trepo.dashView();
	 }
	 
}
