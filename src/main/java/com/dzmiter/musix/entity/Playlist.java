package com.dzmiter.musix.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "PLAYLISTS")
public class Playlist extends AbstractEntity {
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@ManyToMany
	@JoinTable(name = "PLAYLIST_TRACK", joinColumns = 
		{ @JoinColumn(name = "playlist_id", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "track_id", 
		                     referencedColumnName = "id") })
	@Cascade({ CascadeType.SAVE_UPDATE })
	private List<Track> tracks;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private User user;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
}
