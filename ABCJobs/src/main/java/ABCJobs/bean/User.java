package ABCJobs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="abcjobs.userlogin")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private long id;
	
	@Column(name = "email",nullable = false, unique = true)
	private String email; 
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "userPID")
	private long pid;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "userPID", referencedColumnName = "userPID", insertable=false, updatable=false)
	private UserProfile userprofile;
	
	
	public User() {}
	public User(long id, String email, String password, long pid) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.pid = pid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}

	public UserProfile getUserprofile() {
		return userprofile;
	}
	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}
	





	private static boolean _isLoggedIn = false;

	public static void setIsLoggedIn(boolean isLogin) {
		_isLoggedIn = isLogin;
	}

	public static boolean isLoggedIn() {
		return _isLoggedIn;
	}
	
	private static boolean _admin = false;

	public static void setIsAdmin(boolean isAdmin) {
		_admin = isAdmin;
	}

	public static boolean isAdmin() {
		return _admin;
	}
	
	private static boolean _job = false;

	public static void setJob(boolean Job) {
		_job = Job;
	}

	public static boolean hasJob() {
		return _job;
	}
	
	private static boolean _apply = false;

	public static void setApply(boolean A) {
		_apply = A;
	}

	public static boolean hasApplied() {
		return _apply;
	}

	private static boolean _isReset = false;

	public static void setIsReset(boolean isReset) {
		_isReset = isReset;
	}

	public static boolean isReset() {
		return _isReset;
	}
	



}
