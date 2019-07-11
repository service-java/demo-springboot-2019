package lyons.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * ҳ������ʽ������
 * 
 */
@SuppressWarnings("serial")
public class WebFilter implements Filter
{
    
    private FilterConfig config;  
    
    public void destroy() {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        //��ȡFilter�б�������
        String encoding = config.getInitParameter("encoding");  
        if (encoding != null && !"".equals(encoding)) 
        {  
            request.setCharacterEncoding(encoding);  
        }  
        chain.doFilter(request, response);  
        
    }

    @Override
    public void init(FilterConfig config)
        throws ServletException
    {
        this.config = config;
        
    }
    
}
