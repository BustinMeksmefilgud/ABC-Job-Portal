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
@Table(name="abcjobs.threads")
public class Threads {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "threadsID")
	private long id;
	
	@Column(name = "content")
	private String content; 
	
	@Column(name = "userID")
	private long uid;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "userID", referencedColumnName = "userID", insertable=false, updatable=false)
	private User user;

	public Threads() {}
	
	public Threads(long id, String content, long uid, User user) {
		super();
		this.id = id;
		this.content = content;
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
