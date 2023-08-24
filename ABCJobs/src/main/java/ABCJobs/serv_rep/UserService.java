package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import ABCJobs.bean.User;

@Service
@Transactional
public class UserService {
	
	
	@Autowired
	UserRepository repo;
	
	 public void save(User u) {
		 repo.save(u);
	 }
	 public List<User> listAll() {
		 return (List<User>) repo.findAll();
	 }
	 
	 public List<User> adminList() {
		 return (List<User>) repo.adminList();
	 }
	 public User get(Long id) {
		 return repo.findById(id).get();
	 }
	 public User updatePassword(String email, String password) {
		User user = repo.getByEmail(email);
		 
		user.setPassword(password);
		
		return repo.save(user);
	 }
	 public void delete(Long id) {
		 repo.deleteById(id);
	 }

	 public User getByEmail(String keyword) {
		 return repo.getByEmail(keyword);
	 }
	 
	 public User getByPid(Long keyword) {
		 return repo.getByPid(keyword);
	 }
	 
}
