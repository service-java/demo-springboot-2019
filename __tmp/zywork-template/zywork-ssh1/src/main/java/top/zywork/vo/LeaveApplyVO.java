package top.zywork.vo;

import java.util.Date;

/**
 * 请假申请VO对象<br />
 * 创建于2017-10-17
 *
 * @author 王振宇
 * @version 1.0
 */
public class LeaveApplyVO {

    private Integer days;
    private String reason;
    private Date applyTime;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}
