import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.xncoding.pos.converter.StudentConverter;
import com.xncoding.pos.entity.Address;
import com.xncoding.pos.entity.Student;
import com.xncoding.pos.entity.User;
import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-03-31 16:50
 */
public class DemoTest {

    private Student getStudentDetails() {
        Student student = new Student("Jack", "Rose");
        student.setFirstName("Mahesh");
        student.setLastName("Parashar");
        student.setRollNo(1);
        student.setClassName("1st");

        Address address = new Address();
        address.setArea("H.No. 16/3, Preet Vihar.").setCity("Delhi");
        address.setState("Delhi").setCountry("India").setPincode(110012);

        student.setAddress(address);
        return student;
    }

    public static String formatXml(String xml) {
        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            StreamResult res = new StreamResult(new ByteArrayOutputStream());
            serializer.transform(xmlSource, res);
            return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
        } catch (Exception e) {
            return xml;
        }
    }


    /**
     * Security framework of XStream not initialized, XStream is probably vulnerable.
     */
    @Test
    public void demo() {
        XStream xstream = new XStream(new StaxDriver());

        Student student = this.getStudentDetails();

        //Object to XML Conversion
        String xml = xstream.toXML(student);
        System.out.println(formatXml(xml));

        //XML to Object Conversion
        Student student1 = (Student) xstream.fromXML(xml);
        System.out.println(student1);
    }

    /**
     * https://www.yiibai.com/xstream/xstream_custom_converter.html
     */
    @Test
    public void convert() {
        XStream xstream = new XStream(new StaxDriver());
        Student student = this.getStudentDetails();
        xstream.autodetectAnnotations(true);

        // 注册转换器
        xstream.registerConverter(new StudentConverter());

        // Object to XML Conversion
        String xml = xstream.toXML(student);
        System.out.println(formatXml(xml));

//     <?xml version="1.0" encoding="UTF-8"?>
//     <com.xncoding.pos.entity.Student>
//       <name>Jack,Rose</name>
//     </com.xncoding.pos.entity.Student>

    }


    /**
     * https://segmentfault.com/a/1190000012435867
     */
    @Test
    public void readUser() {
            XStream xStream = new XStream();
            xStream.alias("User", User.class);
            String xml = "<User>\n" +
                    "  <userName>lanweihong</userName>\n" +
                    "  <email>lwhhhp@gmail.com</email>\n" +
                    "</User>";
            //转对象
            User user = (User)xStream.fromXML(xml);
            System.out.println(user.toString()); // User:{userName=lanweihong,email=lwhhhp@gmail.com}
            System.out.println(user.getUserName()); // lanweihong
    }


}
