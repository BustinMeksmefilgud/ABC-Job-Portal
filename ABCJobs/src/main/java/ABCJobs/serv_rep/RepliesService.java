package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ABCJobs.bean.Replies;

@Service
@Transactional
public class RepliesService {
	@Autowired
	RepliesRepository Rrepo;
	
	public void save(Replies r) {
		 Rrepo.save(r);
	 }
	 public List<Replies> listAll() {
		 return (List<Replies>) Rrepo.findAll();
	 }
	 public void delete(Long id) {
		 Rrepo.deleteById(id);
	 }
	 public List<Replies> listForThread(Long tid){
		 return (List<Replies>) Rrepo.findCommentReplies(tid);
	 };
	 public Long[] findRid(Long cid) {
		 return (Long[])Rrepo.findRid(cid);
	 }
}
