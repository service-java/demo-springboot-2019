package com.itzcn.bean;

import java.util.ArrayList;
import java.util.List;

public class Classes {
	
	private String[] names = new String[3];						//����String���͵�����
	private List<Student> students = new ArrayList<Student>();	//����List������
	public String[] getNames() {							//��ȡһ������
		return names;
	}
	public void setNames(String[] names) {					//Ϊ���鸳ֵ
		this.names = names;
	}
	public String getNames(int index){					//������������ȡ�����е�ĳ��Ԫ��
		return names[index];
	}
	public void setNames(int index , String name){			//Ϊ�����е�ĳ��Ԫ�ظ�ֵ
		this.names[index] = name;
	}
	public List<Student> getStudents() {					//��ȡһ������
		return students;
	}
	public void setStudents(List<Student> students) {			//Ϊ���ϸ�ֵ
		this.students = students;
	}
	public Student getStudents(int index){					//������������ȡ�����е�ĳ��Ԫ��
		return students.get(index);
	}
	public void setStudents(int index , Student student){			//Ϊ�����е�ĳ��Ԫ�ظ�ֵ
		this.students.set(index, student);
	}
}
