package Test.demo1;

import org.apache.ibatis.session.SqlSession;
import zstu.domain.Report_infoRm;
import  zstu.utils.SessionUtils;
import  zstu.domain.administrator;
import  zstu.dao.*;
import  java.util.List;
import static org.junit.Assert.*;

public class SessionUtilsTest {

    @org.junit.Test
    public void getSqlSession() {
        SqlSession sqlSession = SessionUtils.getSqlSession();
        System.out.println("sdasd");
        administrator admin=new administrator();
        admin.setName("张飞");
        admin.setPassward("123");
        AdministratorDao dao = sqlSession.getMapper(AdministratorDao.class);
        long count=dao.verifyByNameAndPassword(admin);
        System.out.println(count);

       //long ans= sqlSession.select("zstu.dao.UserDao.verifyByNameAndPassword");
    }
    @org.junit.Test
    public void getSqlSession1() {
        SqlSession sqlSession = SessionUtils.getSqlSession();

        AdministratorDao dao = sqlSession.getMapper(AdministratorDao.class);
        List<Report_infoRm> report_info=dao.queryAllReport_infoRM();
        for(Report_infoRm item:report_info)
        {
            System.out.println(item.toString());
        }
        //long ans= sqlSession.select("zstu.dao.UserDao.verifyByNameAndPassword");
    }

}