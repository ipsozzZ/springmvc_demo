package live.ipso.springmvc2.Controller;

import live.ipso.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class MyController {


   @RequestMapping("show1.action")
   public ModelAndView show1(){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("ipso", "this is show1");

      modelAndView.setViewName("result");
      return modelAndView;
   }

   @RequestMapping("show2.action")
   public ModelAndView show2(){
      ModelAndView modelAndView = new ModelAndView();
      int[] num = new int[10];
      for (int i = 0; i<10; i++){
         num[i] = i + 1;
      }
      modelAndView.addObject("arr", num);
      modelAndView.setViewName("result2");
      return modelAndView;
   }

   /**
    * 使用原生的request对象接收参数demo
    * @param request get参数
    * @return View视图模型
    */
   @RequestMapping("show3")
   public ModelAndView show3(HttpServletRequest request){
      String id = request.getParameter("id");

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("id", id);
      modelAndView.setViewName("result3");
      return modelAndView;
   }

   /**
    * 接收简单参数类型与@RequestParam,与参数名一至就会自动对应，并且会自动做类型的转换
    * 假设请求传来的参数名为id，如果接收参数名不一致则必须写成：
    * "@RequestParam(value="id", required=false, defaultValue="100")Integer idKey"
    * required=false可不写，写表示该参数可以不传，默认是required=true
    * defaultValue="100"可不写，写表示默认值，不设置时默认是defaultValue=null
    * @return View视图模型
    */
   @RequestMapping("show4")
   public ModelAndView show4(@RequestParam(value="id", required=false, defaultValue = "100") Integer idkey, String name){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("id", idkey);
      modelAndView.addObject("name", name);
      modelAndView.setViewName("result4");
      return modelAndView;
   }

   /**
    * javaBean接收参数
    * 注意：javaBean类中的属性名必须和请求传递的参数名保持一致，否则无法接收到参数
    * @return View视图模型
    */
   @RequestMapping("show5")
   public ModelAndView show5(User user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("result5");
      return modelAndView;
   }

   /**
    * 数组接收参数
    * 注：这种方式接收的是与数组名相同参数名的值
    * @return View视图模型
    */
   @RequestMapping("show6")
   public ModelAndView show6(String name[]){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("name", Arrays.toString(name));
      modelAndView.setViewName("result6");
      return modelAndView;
   }

   /**
    * javaBean与数组结合使用
    * 注：javaBean中的数组属性名必须和请求参数中的一个或多个参数名相同
    * @return View视图模型
    */
   @RequestMapping("show7")
   public ModelAndView show7(User user){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("result7");
      return modelAndView;
   }

   /**
    * javaBean、数组和包装类结合使用
    * @return View视图模型
    */
   @RequestMapping("show8")
   public ModelAndView show8(User user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("result8");
      return modelAndView;
   }

   /**
    * javaBean、数组和包装类、List集合结合使用
    * @return View视图模型
    */
   @RequestMapping("show9")
   public ModelAndView show9(User user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("result9");
      return modelAndView;
   }

   /**
    * javaBean、数组和包装类、List集合结合使用
    * @return View视图模型
    */
   @RequestMapping("show10")
   public ModelAndView show10(){
      return new ModelAndView();
   }
}
