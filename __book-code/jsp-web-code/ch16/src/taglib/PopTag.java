package taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PopTag extends TagSupport {

	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();

		try {

			out.println("<script language=\"javascript\" >");

			out.println("mpmenu1=new mMenu('��վ��ҳ','NEWdefault.jsp','self','','','','');");

			
			out.println("mwritetodocument();");

			out.println("</script>");

		} catch (Exception e) {
			throw new JspTagException("IOException:" + e.toString());
		}
		return SKIP_BODY;
	}

}
