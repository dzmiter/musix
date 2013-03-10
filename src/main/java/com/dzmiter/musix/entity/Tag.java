package com.dzmiter.musix.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TAGS")
public class Tag extends AbstractEntity {
	
	@Column
	private String name;
	
	@ManyToMany
	@JoinTable(name = "TRACK_TAG", joinColumns = 
		{ @JoinColumn(name = "tag_id", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "track_id", 
		                     referencedColumnName = "id") })
	@Cascade({ CascadeType.SAVE_UPDATE })
	private List<Track> tracks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
}
