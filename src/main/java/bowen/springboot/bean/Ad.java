package bowen.springboot.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Ad {
	
	/**
	 * unique string representing partner
	 */
	@NotEmpty
	private String partner_id;
	
	
	/**
	 * campaign duration in seconds from now
	 */
	@NotNull
	private int duration;
	
	
	/**
	 * content to be displayed as an ad
	 */
	@NotEmpty
	private String ad_content;
	
	private long creationTime;
	
	public Ad() {}

	public Ad(String partner_id, int duration, String ad_content) {
		super();
		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	
}
