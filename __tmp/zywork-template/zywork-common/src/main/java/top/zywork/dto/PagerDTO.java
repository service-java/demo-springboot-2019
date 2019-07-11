package top.zywork.dto;

import java.util.List;

/**
 * 分页对象，包括分页需要的参数pageSize和pageNo及分页结果total和rows
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 * @param <T> 分页组件可以接收指定类型的DTO对象
 */
public class PagerDTO<T> {

    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private List<T> rows;

    public PagerDTO() {}

    public PagerDTO(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getBeginIndex() {
        return (pageNo - 1) * pageSize;
    }

    public Long getTotalPage() {
        long totalPage = total % pageSize;
        return totalPage == 0 ? totalPage / pageSize : totalPage / pageSize + 1;
    }
}
