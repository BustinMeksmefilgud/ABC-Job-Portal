package ABCJobs.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="abcjobs.userprofile")
public class UserProfile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "userPID")
private long id;

@Column(name = "firstname")
private String fname;

@Column(name = "lastname")
private String lname;

@Column(name = "city")
private String city;

@Column(name = "country")
private String country;

@Column(name = "company")
private String company;

@Column(name = "jobID")
private long jid; 

@OneToOne(optional=true)
@JoinColumn(name = "jobID", referencedColumnName = "jobID", insertable=false, updatable=false)
private Job job;

public UserProfile() {}



public UserProfile(long id, String fname, String lname, String city, String country, String company) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.city = city;
	this.country = country;
	this.company = company;
}



public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public long getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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



}
