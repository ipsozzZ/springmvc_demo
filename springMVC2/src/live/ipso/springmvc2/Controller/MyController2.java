package live.ipso.springmvc2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MyController2 {

   /**
    * 自定义转换器测试
    * @param age 年龄
    * @return View视图模型
    */
   @RequestMapping("convert")
   public ModelAndView testConvert(String age, Date date, String name){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("age", age);
      modelAndView.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
      modelAndView.addObject("name", name);
      modelAndView.setViewName("converterResult");
      return modelAndView;
   }
}
