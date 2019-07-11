package top.zywork.common;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Dozer mapper的工具类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class DozerMapperUtils {

    /**
     * 把List<E>列表转成目标List<T>列表
     * @param mapper Dozer的Mapper实例
     * @param eList 需要转换的原始列表对象，支持泛型
     * @param tClass 转换到的目标列表对象的类，支持泛型
     * @param <E> 原始列表内存储的对象
     * @param <T> 目标对象
     * @return 由原始列表转化的目标列表对象
     */
    public static <E, T> List<T> map(Mapper mapper, List<E> eList, Class<T> tClass) {
        List<T> tList = new ArrayList<>();
        for (E e : eList) {
            T t = null;
            try {
                t = tClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            mapper.map(e, t);
            tList.add(t);
        }
        return tList;
    }

    public static List<Object> mapList(Mapper mapper, List<Object> srcList, Class<?> destClass) {
        List<Object> destList = new ArrayList<>();
        for (Object obj : srcList) {
            destList.add(mapper.map(obj, destClass));
        }
        return destList;
    }
}
