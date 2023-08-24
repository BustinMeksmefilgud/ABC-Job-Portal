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
@Table(name="abcjobs.comments")
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentID")
	private long id;
	
	@Column(name = "content")
	private String content; 
	
	@Column(name = "threadsID")
	private long tid;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "threadsID", referencedColumnName = "threadsID", insertable=false, updatable=false)
	private Threads thread;
	
	@Column(name = "userID")
	private long uid;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "userID", referencedColumnName = "userID", insertable=false, updatable=false)
	private User user;

	public Comments() {}
	
	

	public Comments(long id, String content, long tid, Threads thread, long uid, User user) {
		super();
		this.id = id;
		this.content = content;
		this.tid = tid;
		this.thread = thread;
		this.uid = uid;
		this.user = user;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public Threads getThread() {
		return thread;
	}

	public void setThread(Threads thread) {
		this.thread = thread;
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
	
	
	
	
}
