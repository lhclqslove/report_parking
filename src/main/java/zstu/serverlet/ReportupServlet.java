package zstu.serverlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import zstu.domain.Report_infoRm;

//@WebServlet(name = "ReportupServlet")
public class ReportupServlet extends HttpServlet {
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 40;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    private  String GetFiledpath(String filename)
    {
        String tmp=filename.substring(filename.lastIndexOf('.'));
        Random random=new Random();
        int random_number=random.nextInt(1000000);
        String last= getServletContext().getRealPath("/") + UPLOAD_DIRECTORY+ File.separator +random_number+System.currentTimeMillis()+tmp;
        return  last;

    }
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SSSS");

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        String tempPath = "C:\\Users\\HP\\Desktop\\S";
        factory.setRepository(new File("C:\\Users\\HP\\Desktop\\S"));
        System.out.println("临时文件存放位置:"+tempPath);
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        System.out.println("上传路径"+uploadPath);

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        Report_infoRm tmp_report=new Report_infoRm();
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            int x=0;
            System.out.println("sum"+formItems.size());
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据

                for (FileItem item : formItems) {
                    System.out.println(++x);
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String filedname=item.getFieldName();
                        String fileName = new File(item.getName()).getName();
                        String filePath =GetFiledpath(fileName);
                        File storeFile = new File(filePath);
                        //
                        Field tmp=tmp_report.getClass().getDeclaredField(filedname);
                        //System.out.println("zhe li chu cuo l");
                        tmp.setAccessible(true);
                        tmp.set(tmp_report,filePath);

                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);

                    }
                    else
                    {
                        String name = item.getFieldName();
                        String value = item.getString();
                        System.out.println(name+"SSSS"+value);
                        Field tmp=tmp_report.getClass().getDeclaredField(name);
                        //System.out.println("zhe li chu cuo l");
                        tmp.setAccessible(true);
                        if("longitude".equals(name)||"latitude".equals(name))
                        {
                            System.out.println("jing lai l");
                            tmp.set(tmp_report,Double.valueOf(value));
                        }
                        else tmp.set(tmp_report,value);
                        System.out.println("cheng gong"+tmp.get(tmp_report));
                       // System.out.println("zhe li chu cuo 2");
                    }
                }
                System.out.println(tmp_report.toString());
                request.setAttribute("message",
                        "文件上传成功!");
               // System.out.println("seconde print"+x);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("chu cuo l");
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);

    }


}
