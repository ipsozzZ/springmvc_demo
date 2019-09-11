package live.ipso.springmvc5.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Controller
public class MyController {

   /**
    * 实现文件下载
    * 获取rest参数时如果参数有后缀如.jpg等需要使用@RequestMapping("download/{filename:.+}")
    * 否则接收不到参数
    * @param filename 文件名
    * @return return
    */
   @RequestMapping("download/{filename:.+}")
   public ResponseEntity download(@PathVariable String filename, HttpSession session) throws Exception {
      System.out.println(filename);

      // 1. 获取文件路径
      ServletContext servletContext = session.getServletContext();
      String realPath = servletContext.getRealPath("/images/" + filename);
      System.out.println(realPath);

      // 2. 把文件读到程序
      InputStream io = new FileInputStream(realPath);
      filename = URLEncoder.encode(filename, "UTF-8"); // 解决文件名中文乱码问题
      byte[] body = new byte[io.available()];
      io.read(body); // 将body中的数据读到io中

      // 3. 创建响应头
      HttpHeaders httpHeaders = new HttpHeaders();
      /* 设置文件以附件的形式下载 */
      httpHeaders.add("content-Disposition", "attachment;filename=" + filename);

      ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body,httpHeaders, HttpStatus.OK);

      return responseEntity;
   }

   /**
    * 实现文件上传
    * @param file CommonsMultipartFile对象，用来获取form-data表单提交的file文件
    * @return 跳转成功页
    * @throws Exception transferTo异常处理
    */
   @RequestMapping("upload")
   public String upload(@RequestParam("file") CommonsMultipartFile file, HttpSession session){
      System.out.println("表单项name属性：" + file.getName()); // 获取文件名
      System.out.println("文件大小：" + file.getSize());
      System.out.println("文件类型：" + file.getContentType());
      System.out.println("文件名：" + file.getOriginalFilename());

      // 确定文件路径
      ServletContext context = session.getServletContext();
      String realPath = context.getRealPath("/upload");
      System.out.println(realPath);
      // 变成程序中的路径
      File uploadPath = new File(realPath);
      if (!uploadPath.exists()) // 判断路径是否存在，不在就创建路径
         uploadPath.mkdirs();

      // 最终路径 路径+文件名
      String fileName = file.getOriginalFilename();
      uploadPath = new File(uploadPath + "/" + fileName);

      // 开始上传文件
      try {
         file.transferTo(uploadPath);
      } catch (IOException e) {
         e.printStackTrace();
      }

      return "success";
   }

   /**
    * 测试语言切换
    * @return
    */
   @RequestMapping("local")
   public String local(){
      return "local";
   }
}
