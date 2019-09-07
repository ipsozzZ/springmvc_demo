package live.ipso.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

   /**
    * 带数据跳转视图
    * 注匹配请求可以省略.action，转发或重定向到action时不能省略.action
    * @return
    */
   @RequestMapping("/first")
   public ModelAndView show(){

      /* 创建ModelAndView用来存储数据和视图 */
      ModelAndView modelAndView = new ModelAndView();

      /* 设置数据到模型中 */
      modelAndView.addObject("name","ipso-spring-mvc11");

      /* 设置视图jsp */
      modelAndView.setViewName("redirect:/result.jsp"); // 请求重定向
      // modelAndView.setViewName("forward:/WEB-INF/result1.jsp"); // 请求转发forward:可以省略不写，即不写:及之前的关键字则默认为请求转发
      // modelAndView.setViewName("redirect:/second.action"); // 请求重定向到action,这里不能省略.action
      return modelAndView;

   }

   /**
    * 直接跳转视图，不带数据
    * @return
    */
   @RequestMapping("/second.action")
   public String show2(){
      return "/result.jsp";
   }

   /**
    * 直接跳转视图，不带数据,请求重定向
    * @return
    */
   @RequestMapping("/third.action")
   public String show3(){
      return "redirect:/result.jsp";
   }
}
