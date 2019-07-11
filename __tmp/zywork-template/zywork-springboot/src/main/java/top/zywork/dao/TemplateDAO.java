package top.zywork.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.zywork.dos.TemplateDO;

/**
 * TemplateDAO接口<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
public interface TemplateDAO extends JpaRepository<TemplateDO, Long> {
}
