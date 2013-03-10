package com.dzmiter.musix.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "ACTIVITIES")
public class Activity extends AbstractEntity {
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date activityDate;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "track_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Track track;

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
	
}
