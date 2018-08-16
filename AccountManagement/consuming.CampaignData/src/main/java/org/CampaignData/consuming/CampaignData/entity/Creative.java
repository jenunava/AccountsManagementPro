package org.CampaignData.consuming.CampaignData.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is the Creative resource model
 * Creative - is an ad on a digital publisher website
 * @author jnavamshan
 *
 */
//with @JsonIgnoreProperties from the Jackson JSON processing library to indicate that any properties not bound in this type should be ignored.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Creative {	
	
	private int clicks;
	private int conversions;
	private int id;
	private int impressions;
	private String name;
	//Assumption: ParentId is the campaign's id	
	private int parentId;
	private int views;
	
	/**
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}
	
	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
	/**
	 * @return the conversions
	 */
	public int getConversions() {
		return conversions;
	}
	
	/**
	 * @param conversions the conversions to set
	 */
	public void setConversions(int conversions) {
		this.conversions = conversions;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the impressions
	 */
	public int getImpressions() {
		return impressions;
	}
	
	/**
	 * @param impressions the impressions to set
	 */
	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	
	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Creative [clicks=" + clicks + ", conversions=" + conversions + ", id=" + id + ", impressions="
				+ impressions + ", name=" + name + ", parentId=" + parentId + ", views=" + views + "]";
	} 
}