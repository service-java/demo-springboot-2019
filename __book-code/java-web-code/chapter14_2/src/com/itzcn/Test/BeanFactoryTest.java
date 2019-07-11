package com.itzcn.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itzcn.bean.ShowStudent;
public class BeanFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("applicationContext.xml");//���������ļ�
		BeanFactory factory = new XmlBeanFactory(resource);
		ShowStudent show = (ShowStudent) factory.getBean("show");//��ȡBean
		show.showStudent();
	}

}
