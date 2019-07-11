package top.zywork.query;

/**
 * 状态查询对象，可用于更新指定记录的状态
 * 更新记录状态需要提供记录的主键id和status值
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public class StatusQuery extends BaseQuery {

    private static final long serialVersionUID = -8543807353237586473L;
    private Long id;
    private Integer status;

    public StatusQuery() {}

    public StatusQuery(Long id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
