package org.pan.web.book;


public class indentlist {
	private long Id;				//ID���к�
	private long IndentNo;			//�����ű����к�
	private long GoodsNo;			//�����к�
	private int Amount;				//��������

	public indentlist() {
		Id = 0;
		IndentNo = 0;
		GoodsNo = 0;
		Amount = 0;
	}

	public long getId() {
		return Id;
	}

	public void setId(long newId) {
		this.Id = newId;
	}

	public long getIndentNo() {
		return IndentNo;
	}

	public void setIndentNo(long newIndentNo) {
		this.IndentNo = newIndentNo;
	}

	public long getGoodsNo() {
		return GoodsNo;
	}

	public void setGoodsNo(long newGoodsNo) {
		this.GoodsNo = newGoodsNo;
	}
	
	public int getAmount() {
		return Amount;
	}

	public void setAmount(int newAmount) {
		this.Amount = newAmount;
	}
}