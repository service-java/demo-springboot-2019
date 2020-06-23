package com.xncoding.pos.converter;


import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.xncoding.pos.entity.Student;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-04-03 13:31
 */
public class StudentConverter implements Converter {
    public boolean canConvert(Class object) {
        return object.equals(Student.class);
    }
    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        Student student = (Student) value;
        writer.startNode("name");
        writer.setValue(student.getName().getFirstName() + "," + student.getName().getLastName());
        writer.endNode();
    }

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        reader.moveDown();
        String[] nameparts = reader.getValue().split(",");
        Student student = new Student(nameparts[0], nameparts[1]);
        reader.moveUp();
        return student;
    }


}
