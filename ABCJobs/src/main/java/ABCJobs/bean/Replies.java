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
@Table(name="abcjobs.replies")
public class Replies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "repliesID")
	private long id;
	
	@Column(name = "content")
	private String content; 
	
	@Column(name = "commentID")
	private long cid;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "commentID", referencedColumnName = "commentID", insertable=false, updatable=false)
	private Comments comment;
	
	@Column(name = "userID")
	private long uid;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "userID", referencedColumnName = "userID", insertable=false, updatable=false)
	private User user;
	
	public Replies() {}

	public Replies(long id, String content, long cid, Comments comment, long uid, User user) {
		super();
		this.id = id;
		this.content = content;
		this.cid = cid;
		this.comment = comment;
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

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public Comments getComment() {
		return comment;
	}

	public void setComment(Comments comment) {
		this.comment = comment;
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
