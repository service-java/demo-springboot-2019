package org.pan.web.book;


public class shopercar {
	private long goodsId;		//ID���
	private int quanlity;		//ѡ������

	public shopercar(){
		goodsId = 0;
		quanlity = 0;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long newGoodsId) {
		goodsId = newGoodsId;
	}
	
	public long getQuality() {
		return quanlity;
	}

	public void setQuanlity(int newquanlity) {
		quanlity = newquanlity;
	}
}
