package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ABCJobs.bean.Comments;

@Service
@Transactional
public class CommentsService {
	@Autowired
	CommentsRepository Crepo;
	
	public void save(Comments c) {
		 Crepo.save(c);
	 }
	 public List<Comments> listAll() {
		 return (List<Comments>) Crepo.findAll();
	 }
	 public Comments get(Long id) {
		 return Crepo.findById(id).get();
	 }
	 public void delete(Long id) {
		 Crepo.deleteById(id);
	 }
	 public List<Comments> listForThread(Long tid){
		 return (List<Comments>) Crepo.findThreadComments(tid);
	 };
	 
	 public Long[] findCid(Long tid) {
		 return (Long[])Crepo.findCid(tid);
	 }
}
