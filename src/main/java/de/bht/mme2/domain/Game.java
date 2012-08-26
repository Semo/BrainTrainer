package de.bht.mme2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "Game")
@Indexed(index = "hibernate/index")
public class Game {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long ID;

	@Column(name = "Name")
	@Field(index = Index.TOKENIZED, store = Store.NO)
	private String gameName;

	@Column(name = "FileLink")
	private String fileLink;

	@Column(name = "Downloads")
	private int downloads;

	@Column(name = "Availability")
	private boolean isActive;

	public Game(String gameName, String fileLink, int downloads, boolean isActive) {
		this.gameName = gameName;
		this.fileLink = fileLink;
		this.downloads = downloads;
		this.isActive = isActive;
	}

	public Game() {
	}

	public final void setID(Long iD) {
		ID = iD;
	}

	public final String getGameName() {
		return gameName;
	}

	public final String getFileLink() {
		return fileLink;
	}

	public final int getDownloads() {
		return downloads;
	}

	public final boolean isActive() {
		return isActive;
	}

	public final void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public final void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public final void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public final void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Game [ID=" + ID + ", gameName=" + gameName + ", fileLink=" + fileLink + ", downloads=" + downloads
				+ ", isActive=" + isActive + "]";
	}

	public Long getID() {
		return this.ID;
	}
}
