package org.pan.web.book;


public class indent {
	private long Id;				//ID���к�
	private String IndentNo;		//�������
	private long UserId;			//�û����к�
	private String SubmitTime;		//�ύ����ʱ��
	private String ConsignmentTime; //����ʱ��
	private float TotalPrice;		//�ܽ��
	private String content;		    //�û���ע
	private String IPAddress;		//�µ�ʱIP
	private boolean IsPayoff;		//�û��Ƿ��Ѹ���
	private boolean IsSales;		//�Ƿ��ѷ���

	public indent() {
		Id = 0;
		IndentNo = "";
		UserId = 0;
		SubmitTime = "";
		ConsignmentTime = "";
		TotalPrice = 0;
		content = "";
		IPAddress = "";
		IsPayoff = false;
		IsSales = false;
	}
	public long getId() {
		return Id;
	}

	public void setId(long newId){
		this.Id = newId;
	}

	public String getIndentNo() {
		return IndentNo;
	}

	public void setIndentNo(String newIndentNo) {
		this.IndentNo = newIndentNo;
	}

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long newUserId){
		this.UserId = newUserId;
	}

	public String getSubmitTime() {
		return SubmitTime;
	}

	public void setSubmitTime(String newSubmitTime) {
		this.SubmitTime = newSubmitTime;
	}

	public String getConsignmentTime() {
		return ConsignmentTime;
	}

	public void setConsignmentTime(String con_time) {
		ConsignmentTime = con_time;
	}

	public float getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(float newTotalPrice){
		this.TotalPrice = newTotalPrice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String newContent) {
		this.content = newContent;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String newIPAddress) {
		this.IPAddress = newIPAddress;
	}

	public boolean getIsPayoff() {
		return IsPayoff;
	}

	public void setIsPayoff(boolean newIsPayoff){
		this.IsPayoff = newIsPayoff;
	}

	public boolean getIsSales() {
		return IsSales;
	}

	public void setIsSales(boolean newIsSales){
		this.IsSales = newIsSales;
	}

}