package com.hyx.entity;

public class Music {
	private String audio_name;
	private String author_name;
	private String play_url;
	private String timelength;
	private String songname;
	private String imageurl;
	private String lrc;

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getAudio_name() {
		return audio_name;
	}

	public void setAudio_name(String audio_name) {
		this.audio_name = audio_name;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getPlay_url() {
		return play_url;
	}

	public void setPlay_url(String play_url) {
		this.play_url = play_url;
	}

	public String getTimelength() {
		return timelength;
	}

	public void setTimelength(String timelength) {
		this.timelength = timelength;
	}

	@Override
	public String toString() {
		return "Music [audio_name=" + audio_name + ", author_name=" + author_name + ", play_url=" + play_url
				+ ", timelength=" + timelength + ", songname=" + songname + ", imageurl=" + imageurl + ", lrc=" + lrc
				+ "]";
	}

}
