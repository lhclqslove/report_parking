package zstu.serverlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import zstu.dao.AdministratorDao;
import zstu.domain.administrator;
import zstu.utils.SessionUtils;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class verifyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name=request.getParameter("username");
        String paw=request.getParameter("password");

        //PrintWriter out = response.getWriter();
        //out.write(name+" "+paw);
        //System.out.println(name+"SSSS"+paw);
        administrator admin=new administrator();
        admin.setName(name);
        admin.setPassward(paw);
        SqlSession sqlSession = SessionUtils.getSqlSession();
        AdministratorDao dao=sqlSession.getMapper(AdministratorDao.class);
        long cout=dao.verifyByNameAndPassword(admin);
        //System.out.println(cout);
        sqlSession.close();
        if(cout>0)
        {
            System.out.println(request.getContextPath()+"SSSS");
            response.sendRedirect("http://localhost:63342/report_parking/src/main/webapp/layuimini-2/index.html?_ijt=srjt39hhgc36vb9pqj7lovd5alhttp://localhost:8080/layuimini-2/index.html");

        }
        else
        {
            response.sendRedirect(request.getRequestURI());
        }
/*        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");*/
        // 实例化输出流对象
        //map转json
/*        PrintWriter out = response.getWriter();
        Map<String,String> mp=new HashMap<String,String>();
        mp.put("judge", String.valueOf(cout));
        String ans= JSON.toJSONString(mp);
        System.out.println("输出的结果是：" + ans);
        out.write(ans);
        out.close();*/



    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
      /*  String name=request.getParameter("name");
        System.out.println(name);
        PrintWriter out = response.getWriter();
        out.write(name);*/
    }
}
