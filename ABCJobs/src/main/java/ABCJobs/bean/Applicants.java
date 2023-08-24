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
@Table(name="abcjobs.applicants")
public class Applicants {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicantID")
	private long id;
	
	@Column(name = "userID")
	private long uid;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "userID", referencedColumnName = "userID", insertable=false, updatable=false)
	private User user;
	
	@Column(name = "jobID")
	private long jid;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "jobID", referencedColumnName = "jobID", insertable=false, updatable=false)
	private Job job;
	
	@Column(name = "status")
	private String status;
	
	public Applicants() {}

	

	public Applicants(long id, long uid, User user, long jid, Job job, String status) {
		super();
		this.id = id;
		this.uid = uid;
		this.user = user;
		this.jid = jid;
		this.job = job;
		this.status = status;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getJid() {
		return jid;
	}

	public void setJid(long jid) {
		this.jid = jid;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
