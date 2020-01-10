package org.perfectglue.target;

public class ReturnMessage {
	private String notificationId;
	private String msaEventId;
	private String activityId;
	private String preAgreementXml;
	
	public ReturnMessage() {}
	public ReturnMessage(String notificationId, String msaEventId, String activityId, String preAgreementXml) {
		super();
		this.notificationId = notificationId;
		this.msaEventId = msaEventId;
		this.activityId = activityId;
		this.preAgreementXml = preAgreementXml;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getMsaEventId() {
		return msaEventId;
	}

	public void setMsaEventId(String msaEventId) {
		this.msaEventId = msaEventId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getPreAgreementXml() {
		return preAgreementXml;
	}

	public void setPreAgreementXml(String preAgreementXml) {
		this.preAgreementXml = preAgreementXml;
	}
}
