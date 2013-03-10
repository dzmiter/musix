package com.dzmiter.musix.entity;

import java.util.Date;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "COMMENTS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends AbstractEntity {

	@Column
	private String commentText;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "track_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Track track;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
