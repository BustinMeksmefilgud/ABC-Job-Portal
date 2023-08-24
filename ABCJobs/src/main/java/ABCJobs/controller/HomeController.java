package ABCJobs.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ABCJobs.bean.*;
import ABCJobs.serv_rep.*;

@Controller
public class HomeController {
	@Autowired
	private UserService UServ;
	@Autowired
	private UserProfileService UPServ;
	@Autowired
	private JobsService JServ;
	@Autowired
	private ThreadsService TServ;
	@Autowired
	private CommentsService CServ;
	@Autowired
	private ApplicantsService AServ;
	@Autowired
	private RepliesService RServ;
	
	@RequestMapping(value="/") // home page
	public ModelAndView index(HttpServletResponse res) throws Exception {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/home") // home page
	public ModelAndView home(Model model) throws Exception {
		return new ModelAndView("index");
	}
// Registration
	@RequestMapping(value="/registration")
	public ModelAndView registration(HttpServletResponse res) throws Exception {
		return new ModelAndView("registration"); 
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registration(@RequestParam("firstName") String fname, 
			@RequestParam("lastName") String lname, 
			@RequestParam("emailAddress") String email,
			@RequestParam("password") String password,
			User user, UserProfile userProfile, Model model) throws Exception {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String hashedPassword = passwordEncoder.encode(password);
			
			userProfile.setFname(fname);
			userProfile.setLname(lname);
			userProfile.setJid(1);
			UPServ.save(userProfile);
			user.setEmail(email);
			user.setPassword(hashedPassword);
			user.setPid(UPServ.getLatestId());
			UServ.save(user);
			
			model.addAttribute("email", email);
			return "thankyou";
		} catch (Exception e) {
			System.out.println(e);
		}
	    model.addAttribute("errMsg", "Email is already taken");
	    return "registration";
	}
	
	@RequestMapping(value="/thankyou")
	public ModelAndView thankyou(HttpServletResponse res) throws Exception {
		return new ModelAndView("thankyou"); 
	}
	
	@RequestMapping(value="/verified")
	public ModelAndView verified() throws Exception {
		return new ModelAndView("verified"); 
	}
	
// Login	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletResponse res, Model model) throws Exception {
		if (User.isLoggedIn()) {
		return new ModelAndView("dashboard"); 
		}
		return new ModelAndView("login");
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST) // login process
//	public String login(HttpSession session, @RequestParam("emailAddress") String email,
//			@RequestParam("password") String password, Model model) throws Exception {
//		try {
//			User foundUser = UServ.getByEmail(email);
//			UserProfile userProfile = UPServ.get(foundUser.getPid());
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			if(email.toLowerCase().equals(foundUser.getEmail().toLowerCase()) 
//					&& passwordEncoder.matches(password, foundUser.getPassword())) {
//				session.setAttribute("User", foundUser);
//				session.setAttribute("Profile", userProfile);
//				session.setAttribute("Pid", userProfile.getId());
//				session.setAttribute("Uid", foundUser.getId());
//				Long AdminID = foundUser.getId();
//				if(AdminID == 1) {
//					User.setIsAdmin(true);
//					session.setAttribute("adminName", userProfile.getFname());
//				}
//				this.setModel(model, session);
//				User.setIsLoggedIn(true);
//				
//			}else{
//				String msg = "Incorrect Credentials!";
//				model.addAttribute("message", msg);
//				return "login";
//			}
//			return "profile";
//		} catch (Exception e) {
//			String msg = "User Not Found";
//			model.addAttribute("message", msg);
//			return "login";
//			
//		}
//		
//		
//	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST) // login process
	public String login(@RequestParam("emailAddress") String email,
			@RequestParam("password") String password, Model model,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String rememberMe = req.getParameter("rememberMe");
			HttpSession session = req.getSession();
			
			User foundUser = UServ.getByEmail(email);
			UserProfile userProfile = UPServ.get(foundUser.getPid());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(email.toLowerCase().equals(foundUser.getEmail().toLowerCase()) 
					&& passwordEncoder.matches(password, foundUser.getPassword())) {
				if (rememberMe != null) { // remember me checked
		             Cookie eCookie = new Cookie("email", foundUser.getEmail());
		             eCookie.setMaxAge(10 * 60); // 10 minutes
		             res.addCookie(eCookie);

		             Cookie iCookie = new Cookie("userId", String.valueOf(foundUser.getId()));
		             iCookie.setMaxAge(10 * 60);
		             res.addCookie(iCookie);
		        }
				
				session.setAttribute("User", foundUser);
				session.setAttribute("Profile", userProfile);
				session.setAttribute("Pid", userProfile.getId());
				session.setAttribute("Uid", foundUser.getId());
				session.setAttribute("isLoggedIn", true);
				
				Long JobId = foundUser.getUserprofile().getJid();
				
				if (JobId != 1) {
					session.setAttribute("hasJob", true);
					
				}
				
				Long AdminID = foundUser.getId();
				if(AdminID == 1) {
					session.setAttribute("isAdmin", true);
					this.setModel(model, session);
					session.setAttribute("adminName", userProfile.getFname());
					return "dashboard";
				}
				this.setModel(model, session);
				
			}else{
				String msg = "Incorrect Credentials!";
				model.addAttribute("message", msg);
				return "login";
			}
			
			return "dashboard";
		} catch (Exception e) {
			String msg = "User Not Found";
			model.addAttribute("message", msg);
			return "login";
			
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse res, Model model) throws Exception {
		session.invalidate();
		return "login"; 
	}
	
	@RequestMapping(value="/forgot-password")
	public ModelAndView forgotPassword(HttpServletResponse res) throws Exception {
		return new ModelAndView("forgot-password");  
	}
	
	@RequestMapping(value="/forgot-password", method = RequestMethod.POST) // forgot password logic
	public String forgotPassword(HttpSession session, @RequestParam("emailAddress") String email,
			Model model) throws Exception {
		
		try {
			User foundUser = UServ.getByEmail(email);
			if(email.toLowerCase().equals(foundUser.getEmail().toLowerCase())) {
				session.setAttribute("email", email);
				session.setAttribute("isReset", true);
				return "reset-password";
			}
		} catch (Exception e) {
			System.out.println(email + " " + e);
		}
				
		String msg = "Email not found";
		model.addAttribute("message", msg);
		return "forgot-password";
	}
	
	@RequestMapping(value="/reset")
	public ModelAndView reset(Model model, HttpSession session) throws Exception {
		if((Boolean) session.getAttribute("isReset") != null) {
			
			return new ModelAndView("reset-password");  
		}
		String msg = "Email required";
		model.addAttribute("message", msg);
		return new ModelAndView("forgot-password"); 
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.POST) // reset password
	public String reset(HttpSession session, @RequestParam("password") String password,
			User user, Model model) throws Exception {
		String email = String.valueOf(session.getAttribute("email"));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(password);
		UServ.updatePassword(email ,hashedPassword);
		
		String msg = "Password has been changed";
		model.addAttribute("scMessage", msg);
		return "login";
	}
	
// Profile
	@RequestMapping(value="/profile") // profile overview
	public ModelAndView dashboard(Model model, HttpSession session) throws Exception {
		try {
			this.setModel(model, session);
			return new ModelAndView("profile");  
		} catch (Exception e) {
			String msg = "Login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="/update-profile") // update profile GET
	public ModelAndView profile(Model model, HttpSession session) throws Exception {
		try {
			this.setModel(model, session);
			return new ModelAndView("update-profile"); 
		} catch (Exception e) {
			String msg = "Login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="/profile-update", method = RequestMethod.POST) // update profile POST
	public String up(HttpSession session, 
			@RequestParam("firstName") String fname, 
			@RequestParam("lastName") String lname,
			@RequestParam("country") String country,
			@RequestParam("city") String city, 
			@RequestParam("company") String company,
			UserProfile userProfile, Model model) {
		userProfile.setFname(fname);
		userProfile.setLname(lname);
		Long UPid = Long.parseLong(String.valueOf(session.getAttribute("Pid")));
		UPServ.update(UPid, userProfile);
		this.setModel(model, session);
		
		String msg = "Profile has been updated";
		model.addAttribute("message", msg);
		return "profile";
	}
	
	@RequestMapping(value="/jobResign")
	public ModelAndView resignJob(Model model, HttpSession session) {
		Long Uid = Long.parseLong(String.valueOf(session.getAttribute("Uid")));
		User user = UServ.get(Uid);
		UserProfile up = user.getUserprofile();
		Applicants ap = AServ.getLatestAccepted(Uid);
		
		up.setCompany(" ");
		up.setJid(1);
		UPServ.save(up);
		AServ.delete(ap.getId());
		this.setModel(model, session);
		session.setAttribute("hasJob", null);
		
		String msg = "You don't work there anymore";
		model.addAttribute("message", msg);
		return new ModelAndView("profile");  
	}
	
	@RequestMapping(value="/deleteThreadUser", method = RequestMethod.GET) 
	public String deleteThreadUser(@RequestParam("Tid") Long tid) {
			int x;
			int y;
			
			Long[] cid = CServ.findCid(tid);
			for (x = 0; x < cid.length; x++) {
				Long[] rid = RServ.findRid(cid[x]);
				for (y = 0; y < rid.length; y++) {
					RServ.delete(rid[y]);
				}
				CServ.delete(cid[x]);
			}
			TServ.delete(tid);
		return "redirect:/profile";
	}
	
	
// Dashboard
	@RequestMapping(value="/dashboard") // profile overview
	public ModelAndView homeDasboard(Model model, HttpSession session) throws Exception {
		try {
			this.setModel(model, session);
			return new ModelAndView("dashboard");  
		} catch (Exception e) {
			String msg = "Login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="/saveApplicant", method = RequestMethod.POST)
	public ModelAndView applicantCreate(@RequestParam("Jid") Long Jid,
			Applicants applicant, Model model, HttpSession session) throws Exception {
		Long Uid = Long.parseLong(String.valueOf(session.getAttribute("Uid")));
		Applicants exists = AServ.getExisting(Jid, Uid);
		this.setModel(model, session);
		if (exists == null) {
			applicant.setUid(Uid);
			applicant.setJid(Jid);
			applicant.setStatus("Pending");
			AServ.save(applicant);
			
			
			String msg = "You have applied for a new job, check back again to see if you made it";
			model.addAttribute("message", msg);
			return new ModelAndView("dashboard"); 
		}
		String msg = "You're already applying for that, you can't apply twice";
		model.addAttribute("message", msg);
		return new ModelAndView("dashboard"); 
	}
	
	
	
	@RequestMapping(value="/savePost", method = RequestMethod.POST)
	public String savePost(@RequestParam("postContent") String content, 
			@RequestParam("uid") Long uid, 
			Threads thread, Model model, HttpSession session) throws Exception {
		if (content != null && !content.equals("")) {
		thread.setContent(content);
		thread.setUid(uid);
		TServ.save(thread);
		this.setModel(model, session);
		String msg = "Posted";
		model.addAttribute("message", msg);
		}
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/viewProfile", method = RequestMethod.GET)
	public ModelAndView viewProfile(@RequestParam("Fname") String name, Model model, HttpSession session) throws Exception {
		Long LoggedUid = Long.parseLong(String.valueOf(session.getAttribute("Uid")));
		UserProfile userProfile = UPServ.getByName(name);
		Long Pid = userProfile.getId();
		User user = UServ.getByPid(Pid);
		Long Uid = user.getId();
		List<Threads> thread = TServ.listAllSpecific(Uid);
		
		if (LoggedUid == Uid) {
			this.setModel(model, session);
			return new ModelAndView("profile");  
		}
		
		model.addAttribute("f", userProfile.getFname().charAt(0));
		model.addAttribute("l", userProfile.getLname().charAt(0));
		
		model.addAttribute("firstName", userProfile.getFname());
		model.addAttribute("lastName", userProfile.getLname());
		
		model.addAttribute("fullName", userProfile.getFname() + " " + userProfile.getLname());
		model.addAttribute("city", userProfile.getCity());
		model.addAttribute("country", userProfile.getCountry());
		model.addAttribute("company", userProfile.getCompany());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("title", user.getUserprofile().getJob().getName());
		model.addAttribute("post", thread);
		
		
		return new ModelAndView("result");  
	}
	
	@RequestMapping(value="/thread", method = RequestMethod.GET) // profile overview
	public ModelAndView threadView(@RequestParam("Tid") Long Tid, Model model, HttpSession session) throws Exception {
		Threads thread = TServ.get(Tid);
		Long Pid = thread.getUser().getUserprofile().getId();
		UserProfile userProfile = UPServ.get(Pid);
		User user = UServ.getByPid(Pid);
		
		
		model.addAttribute("tid", Tid);
		model.addAttribute("threadFname", userProfile.getFname());
		model.addAttribute("tf", userProfile.getFname().charAt(0));
		model.addAttribute("tl", userProfile.getLname().charAt(0));
		model.addAttribute("temail", user.getEmail());
		model.addAttribute("tcontent", thread.getContent());
		
		List<Comments> comments = CServ.listForThread(Tid);
		List<Replies> replies = RServ.listAll();
		

		model.addAttribute("comments", comments);
		model.addAttribute("replies", replies);
		try {
			this.setModel(model, session);
			return new ModelAndView("thread");  
		} catch (Exception e) {
			String msg = "Login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	@RequestMapping(value="/saveComment", method = RequestMethod.POST)
	public String saveComment(@RequestParam("comment") String content, 
			@RequestParam("tid") Long tid, @RequestParam("uid") Long uid,
			Comments comment, Model model, HttpSession session) throws Exception {
		if (content != null && !content.equals("")) {
		comment.setContent(content);
		comment.setTid(tid);
		comment.setUid(uid);
		CServ.save(comment);
		}
		
		this.setModel(model, session);
		return "redirect:/thread?Tid=" + tid;
	}
	
	@RequestMapping(value="/saveReply", method = RequestMethod.POST)
	public String saveReply(@RequestParam("reply") String content, 
			@RequestParam("tid") Long tid, @RequestParam("uid") Long uid, @RequestParam("cid") Long cid,
			Replies reply, Model model, HttpSession session) throws Exception {
		if (content != null && !content.equals("")) {
		reply.setContent(content);
		reply.setCid(cid);
		reply.setUid(uid);
		RServ.save(reply);
		}
		
		this.setModel(model, session);
		return "redirect:/thread?Tid=" + tid;
	}
	
// Search
	@RequestMapping(value="/search")
	public ModelAndView search(HttpServletRequest req, HttpSession session, Model model) throws Exception {
		String search = req.getParameter("q");
		
		if(search != null && !search.equals(" ")) {
			search = search.split(" ")[0];
			List<UserProfile> searchedUser = UPServ.search(search);
			model.addAttribute("selected", searchedUser);
			
		         if(searchedUser.size() == 0) {
				model.addAttribute("notFound", true);
			}
	}
		
	
		
		return new ModelAndView("search");  
	}
	
	@RequestMapping(value="/result", method = RequestMethod.POST) // view someone profile
	public ModelAndView searchProfile(@RequestParam("Pid") Long Pid, Model model, HttpSession session) throws Exception {
		Long LoggedUid = session.getAttribute("Uid") != null ? Long.parseLong(String.valueOf(session.getAttribute("Uid"))) : null;
		UserProfile userProfile = UPServ.get(Pid);
		User user = UServ.getByPid(Pid);
		Long Uid = user.getId();
		List<Threads> thread = TServ.listAllSpecific(Uid);
		
		if (LoggedUid != null && LoggedUid.equals(Uid)) {
			this.setModel(model, session);
			return new ModelAndView("profile");  
		}
		
		model.addAttribute("f", userProfile.getFname().charAt(0));
		model.addAttribute("l", userProfile.getLname().charAt(0));
		
		model.addAttribute("firstName", userProfile.getFname());
		model.addAttribute("lastName", userProfile.getLname());
		
		model.addAttribute("fullName", userProfile.getFname() + " " + userProfile.getLname());
		model.addAttribute("city", userProfile.getCity());
		model.addAttribute("country", userProfile.getCountry());
		model.addAttribute("company", userProfile.getCompany());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("title", user.getUserprofile().getJob().getName());
		model.addAttribute("post", thread);
		return new ModelAndView("result");  
	}
	
// Admin
	@RequestMapping(value="/admin-dash") // home page
	public ModelAndView admin_dash(Model model, HttpSession session) throws Exception {
		try {
			this.setModel(model, session);
			if ((Boolean) session.getAttribute("isAdmin") != null) {
				List<User> user = UServ.adminList();
				model.addAttribute("user", user);
				return new ModelAndView("admin-dash");
			}
			String msg = "You don't have access to that";
			model.addAttribute("message", msg);
			return new ModelAndView("profile");
		} catch (Exception e) {
			String msg = "Admin login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="/deleteUser", method = RequestMethod.GET) 
	public String deleteUser(@RequestParam("Uid") Long id, Model model) {
		if(id != 1) {
			User user = UServ.get(id);
			Long Pid = user.getPid();
			UServ.delete(id);
			UPServ.delete(Pid);
			String msg = "Rip Bozo";
			model.addAttribute("changed", msg);
		} else {
			String msg = "Don't do that";
			model.addAttribute("changed", msg);
		}
		
		return "redirect:/admin-dash";
	}
	
	
	@RequestMapping(value = "/admin-up")
	public ModelAndView admin_up(@RequestParam("Uid") Long id, Model model, HttpSession session) {
		User user = UServ.get(id);
		
		model.addAttribute("id", user.getPid());
		model.addAttribute("f", user.getUserprofile().getFname().charAt(0));
		model.addAttribute("l", user.getUserprofile().getLname().charAt(0));
		
		model.addAttribute("firstName", user.getUserprofile().getFname());
		model.addAttribute("lastName", user.getUserprofile().getLname());
		
		model.addAttribute("fullName", user.getUserprofile().getFname() + " " + user.getUserprofile().getLname());
		model.addAttribute("city", user.getUserprofile().getCity());
		model.addAttribute("country", user.getUserprofile().getCountry());
		model.addAttribute("company", user.getUserprofile().getCompany());
		model.addAttribute("title", user.getUserprofile().getJob().getName());
		model.addAttribute("email", user.getEmail());
	
		return new ModelAndView("admin-up");
	}
	
	
	@RequestMapping(value="/admin-up-process", method = RequestMethod.POST) // update profile POST
	public String admin_up_process(HttpSession session,
			@RequestParam("UPid") String UPid,
			@RequestParam("firstName") String fname, 
			@RequestParam("lastName") String lname,
			@RequestParam("country") String country,
			@RequestParam("city") String city, 
			@RequestParam("company") String company, UserProfile userProfile, Model model) {
		
		Long Pid = Long.parseLong(String.valueOf(UPid));
		userProfile.setFname(fname);
		userProfile.setLname(lname);
		UPServ.update(Pid, userProfile);
	
		String msg = "A New Identity is Born";
		model.addAttribute("changed", msg);
		return "redirect:/admin-dash";
	}
	
	@RequestMapping(value="/admin-job") // home page
	public ModelAndView admin_job(Model model, HttpSession session) throws Exception {
		try {
			this.setModel(model, session);
			if ((Boolean) session.getAttribute("isAdmin") != null) {
				List<Applicants> applicants = AServ.getPending();
				List<Job> job = JServ.adminList();
				model.addAttribute("forApproval", applicants);
				model.addAttribute("jobs", job);
				return new ModelAndView("admin-job");
			}
			String msg = "You don't have access to that";
			model.addAttribute("message", msg);
			return new ModelAndView("profile");
		} catch (Exception e) {
			String msg = "Admin login required";
			model.addAttribute("message", msg);
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="/saveJob", method = RequestMethod.POST)
	public String saveJob(@RequestParam("jobName") String name, 
			@RequestParam("jobDescription") String description,
			@RequestParam("company") String company, 
			@RequestParam("salary") String salary, 
			@RequestParam("hours") String hours, 
			Job job, Model model) throws Exception {
		
		job.setName(name);
		job.setDesc(description);
		job.setCompany(company);
		job.setSalary(salary);
		job.setHours(hours);
		job.setStatus("OPEN");
		JServ.save(job);
		
		return "redirect:/admin-job";
	}
	
	@RequestMapping(value="/jobAccept", method = RequestMethod.POST)
	public String acceptJob(@RequestParam("Aid") Long Aid, Model model, HttpSession session)throws Exception {
		
		Applicants applicant = AServ.get(Aid);
		Long Uid = applicant.getUid();
		Long Jid = applicant.getJid();
		User user = UServ.get(Uid);
		UserProfile up = user.getUserprofile();
		Applicants check = AServ.getLatestAccepted(Uid);
		
		if(check == null) {
			up.setCompany(applicant.getJob().getCompany());
			up.setJid(Jid);
			applicant.setStatus("Accepted");
			UPServ.save(up);
			AServ.save(applicant);
			this.setModel(model, session);
			String msg = "That guy has been accepted";
			model.addAttribute("message", msg);
			List<Applicants> applicants = AServ.getPending();
			List<Job> job = JServ.adminList();
			model.addAttribute("forApproval", applicants);
			model.addAttribute("jobs", job);
			return "admin-job";  
		}
		
		String msg = "That guy already has a job";
		model.addAttribute("message", msg);
		List<Applicants> applicants = AServ.getPending();
		List<Job> job = JServ.adminList();
		model.addAttribute("forApproval", applicants);
		model.addAttribute("jobs", job);
		return "admin-job"; 
	}
	
	@RequestMapping(value="/jobReject", method = RequestMethod.POST)
	public String rejectJob(@RequestParam("Aid") Long Aid, Model model, HttpSession session)throws Exception {
		
		Applicants applicant = AServ.get(Aid);

	
		applicant.setStatus("Rejected");
		AServ.save(applicant);
		this.setModel(model, session);
		
		String msg = "That guy has been rejected";
		model.addAttribute("message", msg);
		List<Applicants> applicants = AServ.getPending();
		List<Job> job = JServ.adminList();
		model.addAttribute("forApproval", applicants);
		model.addAttribute("jobs", job);
		return "admin-job";  
	}
	
	@RequestMapping(value="/jobClose", method = RequestMethod.GET)
	public String closeJob(@RequestParam("Jid") Long Jid, Model model, HttpSession session) {
		Job job = JServ.get(Jid);
		job.setStatus("CLOSE");
		JServ.save(job);
		
		String msg = "Job Closed";
		model.addAttribute("message", msg);
		return "redirect:/admin-job";  
	}
	@RequestMapping(value="/jobOpen", method = RequestMethod.GET)
	public String openJob(@RequestParam("Jid") Long Jid, Model model, HttpSession session) {
		Job job = JServ.get(Jid);
		job.setStatus("OPEN");
		JServ.save(job);
		
		String msg = "Job Opened";
		model.addAttribute("message", msg);
		return "redirect:/admin-job"; 
	}
	@RequestMapping(value="/deleteThreadAdmin", method = RequestMethod.GET) 
	public String deleteThreadAdmin(@RequestParam("Tid") Long tid) {
			int x;
			int y;
			
			Long[] cid = CServ.findCid(tid);
			for (x = 0; x < cid.length; x++) {
				Long[] rid = RServ.findRid(cid[x]);
				for (y = 0; y < rid.length; y++) {
					RServ.delete(rid[y]);
				}
				CServ.delete(cid[x]);
			}
			TServ.delete(tid);
		return "redirect:/dashboard";
	}
// Model
	private void setModel(Model model, HttpSession session) {
		Long Uid = Long.parseLong(String.valueOf(session.getAttribute("Uid")));
		User user = UServ.get(Uid);
		List<Threads> thread = TServ.listAllSpecific(Uid);
		List<Job> allJobs = JServ.findOpen();
		List<Threads> allThreads = TServ.dashboard();
		List<Applicants> latest = AServ.getLatest(Uid);
		
		model.addAttribute("f", user.getUserprofile().getFname().charAt(0));
		model.addAttribute("l", user.getUserprofile().getLname().charAt(0));
		
		model.addAttribute("firstName", user.getUserprofile().getFname());
		model.addAttribute("lastName", user.getUserprofile().getLname());
		
		model.addAttribute("fullName", user.getUserprofile().getFname() + " " + user.getUserprofile().getLname());
		model.addAttribute("city", user.getUserprofile().getCity());
		model.addAttribute("country", user.getUserprofile().getCountry());
		model.addAttribute("company", user.getUserprofile().getCompany());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("title", user.getUserprofile().getJob().getName());
		model.addAttribute("uid", user.getId());
		model.addAttribute("jid", user.getUserprofile().getJid());
		model.addAttribute("yourpost", thread);
		model.addAttribute("threads", allThreads);
		model.addAttribute("jobs", allJobs);
		model.addAttribute("application", latest);

		Long Jid = user.getUserprofile().getJid();	
		
		if (Jid != 1) {
			session.setAttribute("hasJob", true);
		} else {
			session.setAttribute("hasJob", null);
		}
	}
}
