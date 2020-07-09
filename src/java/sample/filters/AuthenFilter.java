/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.filters;

import sample.controllers.CreatUserAccountController;
import sample.controllers.LogoutController;
import sample.controllers.UpdateAccountInfoController;
import sample.dtos.UserDTO;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saost
 */
public class AuthenFilter implements Filter {
    private static List<String> userResource;
    private static List<String> adminResource;
    private static String LOGIN_PAGE = "login.jsp";
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenFilter() {
        userResource = new ArrayList<>();
        adminResource = new ArrayList<>();
        
        userResource.add("");
        userResource.add("login.jsp");
        userResource.add("MainController");
        userResource.add("LoginController");
        userResource.add("LogoutController");
        userResource.add("test_false.html");
        userResource.add("test_success.html");

        adminResource.add("");
        adminResource.add("login.jsp");
        adminResource.add("MainController");
        adminResource.add("LoginController");
        adminResource.add("LogoutController");
        adminResource.add("test_false.html");
        adminResource.add("test_success.html");
        
        adminResource.add("ManageAccountController");
        adminResource.add("UpdateAccountInfoController");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest rq = (HttpServletRequest) request;
            HttpServletResponse rs = (HttpServletResponse) response;
            String uri = rq.getRequestURI();
            log(uri);
            if ((uri.contains(".js")
                    || uri.contains(".jpg")
                    || uri.contains("MainController")
                    || uri.contains(".png")
                    || uri.contains(".jpeg"))
                    && !uri.contains(".jsp")) {
                chain.doFilter(request, response);
                return;
            } else {
                if (uri.contains("login.jsp")
                        || uri.contains("MainController")
                        || uri.contains("LoginController")
                        || uri.contains(LogoutController.class.getSimpleName())
                        || uri.contains(CreatUserAccountController.class.getSimpleName())
                        || uri.contains("creat_user_account.jsp")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            HttpSession session = rq.getSession();
            if (session == null || session.getAttribute("AUTH_USER") == null) {
                rs.sendRedirect(LOGIN_PAGE);
            } else {
                UserDTO dto = (UserDTO) session.getAttribute("AUTH_USER");
                String role = dto.getRoleID();
                if (role.equals("AD") && adminResource.contains(resource)) {
                    chain.doFilter(request, response);
                } else if (role.equals("US") && userResource.contains(resource)){
                    chain.doFilter(request, response);
                } else {
                    rs.sendRedirect(LOGIN_PAGE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
