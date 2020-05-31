package zstu.dao;
import zstu.domain.Report_infoRm;
import  zstu.domain.administrator;

import java.util.List;

public interface AdministratorDao {
    public  long verifyByNameAndPassword(administrator administrator);
    public List<Report_infoRm> queryAllReport_infoRM();
}
