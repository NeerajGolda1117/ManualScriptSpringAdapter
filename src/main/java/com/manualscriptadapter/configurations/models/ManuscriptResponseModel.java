package com.manualscriptadapter.configurations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ManuscriptResponseModel {
	
	
	private Long articleId;
	
	private String authorName;
	
	private String title;
	
	private String shortTitle;
	
	private String authorEmailAddress;
	
	private boolean isActive;
	
	private boolean isPublished;
	
	private int pages;
	


	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getAuthorEmailAddress() {
		return authorEmailAddress;
	}

	public void setAuthorEmailAddress(String authorEmailAddress) {
		this.authorEmailAddress = authorEmailAddress;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "ManuscriptResponseModel [articleId=" + articleId + ", authorName=" + authorName + ", title=" + title
				+ ", shortTitle=" + shortTitle + ", authorEmailAddress=" + authorEmailAddress + ", isActive=" + isActive
				+ ", isPublished=" + isPublished + ", pages=" + pages +  "]";
	}
	
	
	
	
	

}
