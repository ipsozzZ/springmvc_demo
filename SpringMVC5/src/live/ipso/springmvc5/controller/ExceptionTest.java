package live.ipso.springmvc5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionTest {

   /**
    * 测试异常，这里抛出算术异常
    * 所以会先去找算术异常，如果没有算术异常就找运行时异常(RuntimeException),再没有就找Exception异常
    * 如果都没有，就找注解@ControllerAdvice(标记为异常处理控制器类)修饰的类中的异常处理
    * @return return
    */
   @RequestMapping("exception")
   public String exceptionTest(){
      int sum = 3 / 0;
      return "success";
   }

   /**
    * 接收异常并处理异常
    * @param ex 接收的异常
    * @param model model
    * @return return
    */
   @ExceptionHandler(value = ArithmeticException.class)
   public String doException(Exception ex, Model model){
      System.out.println(ex.getMessage());
      model.addAttribute("message", ex.getMessage());
      return "error";
   }

   /**
    * 接收异常并处理异常
    * @param ex 接收的异常
    * @param model model
    * @return return
    */
   @ExceptionHandler(value = RuntimeException.class)
   public String doException1(Exception ex, Model model){
      System.out.println(ex.getMessage());
      model.addAttribute("message", ex.getMessage());
      return "error";
   }

   /**
    * 接收异常并处理异常
    * @param ex 接收的异常
    * @param model model
    * @return return
    */
   @ExceptionHandler(value = Exception.class)
   public String doException2(Exception ex, Model model){
      System.out.println(ex.getMessage());
      model.addAttribute("message", ex.getMessage());
      return "error";
   }
}
