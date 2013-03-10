package com.dzmiter.musix.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TRACKS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Track extends AbstractEntity {

	@Column
	private String name;
	
	@Column
	private String artist;
	
	@Column
	private Integer rating;
	
	@Column
	private Integer playsnumber;
	
	@Column
	private String path;
	
	@Column
	private Integer size;
	
	@Column
	private Integer bitrate;
	
	@Column
	private String format;
	
	@Column
	private String description;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;	
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private User user;
	
	@OneToMany(mappedBy = "track")
	private List<Comment> comments;
	
	@ManyToMany(mappedBy = "tracks")
	private List<Playlist> playlists;
	
	@ManyToMany(mappedBy = "tracks")
	private List<Tag> tags;
	
	@OneToMany(mappedBy = "track")
	private List<Activity> activities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getPlaysnumber() {
		return playsnumber;
	}

	public void setPlaysnumber(Integer playsnumber) {
		this.playsnumber = playsnumber;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
}
