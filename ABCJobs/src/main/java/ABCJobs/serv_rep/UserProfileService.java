package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ABCJobs.bean.UserProfile;

@Service
@Transactional
public class UserProfileService {
	@Autowired
	UserProfileRepository repo;
	 public void save(UserProfile up) {
		 repo.save(up);
	 }
	 public List<UserProfile> listAll() {
		 return (List<UserProfile>) repo.findAll();
	 }
	 public UserProfile get(Long id) {
		 return repo.findById(id).get();
	 }
	 public UserProfile update(Long Pid, UserProfile up) {
		 UserProfile userProfile = repo.findById(Pid).get();
		 
		 userProfile.setFname(up.getFname());
		 userProfile.setLname(up.getLname());
		 userProfile.setCity(up.getCity());
		 userProfile.setCountry(up.getCountry());
		 userProfile.setCompany(up.getCompany());
		 
		 return repo.save(userProfile);
	 }
	 public void delete(Long id) {
		 repo.deleteById(id);
	 }
	 public int getLatestId() {
		 return repo.getLatestId();
	 }
	 public List<UserProfile> search(String keyword) {
		 return repo.search('%'+keyword+'%');
	 }
	 public String searchID(String keyword) {
		 return repo.searchID(keyword);
	 }
	 public UserProfile getByName(String name) {
		 return repo.getByName(name);
	 }
	 
}
