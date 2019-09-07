package live.ipso.springmvc2.Controller;

import javafx.scene.transform.Scale;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController3 {


   /*---------------  RequestMapping  ----------------*/
   /**
    * RequestMapping的value属性用来设置请求路径，可以设置多个路径合适如下
    * @param user 参数user
    * @return  modelAndView视图模型
    */
   @RequestMapping(value = {"testrequestmapping1", "testrequestmapping2"})
   public ModelAndView testRequest(String user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * RequestMapping的method属性，单个方式的时候就不用写成集合的形式，默认是所有方式均可以接收
    * @param user 参数user
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "testmethod", method = {RequestMethod.GET, RequestMethod.POST})
   public ModelAndView testmethod(String user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * RequestMapping的params属性，设定请求参数中必须具备的参数，否侧无法请求当前方法
    * @param user 参数user
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "testparams", params = {"name=ipso", "age=20"})
   public ModelAndView testparams(String user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * RequestMapping的header属性，发送的请求头必须要与设置的请求头相同时，才能访问到对应的方法
    * @param user 参数user testant
    * @return modelAndView视图模型
    */
   @RequestMapping(
           value = "testheaders",
           headers = {"Host=localhost:8080", "Referer=http://localhost:8080/requestMapping.jsp"})
   public ModelAndView testheaders(String user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * RequestMapping的ant风格地址,请求路径的一种匹配方法
    * @param user 参数user
    * @return modelAndView视图模型
    */
   // @RequestMapping(value = "testheaders/??") // testheaders/ + 任意两个字符
   // @RequestMapping(value = "testheaders/*/ipso") // testheaders/ + 任意字符 + /ipso
   @RequestMapping(value = "testheaders/**/ipso") // testheaders/ + 多重路径(.../*/*...) + /ipso
   public ModelAndView testant(String user){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /*---------------  PathVariable  ----------------*/

   /**
    * 接收rest风格参数
    * @param user 用户名
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "rest/{user}")
   public ModelAndView rest(@PathVariable String user){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", "rest=====" + user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * 测试接收form表单提交的put、delete数据
    * @param user 用户名
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "testmethod/{user}", method = RequestMethod.PUT)
   public ModelAndView testrestmethod(@PathVariable String user){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", "user====" + user);
      modelAndView.setViewName("redirect:/secondMethod.action");
      return modelAndView;
   }

   /**
    * 跳转视图 testHeader
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "secondMethod")
   public ModelAndView secondMethod(String user){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", user);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * 测试头信息
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "testHeader")
   public ModelAndView testHeader(@RequestHeader("Cookie") String cookie, @RequestHeader("Host") String host){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", "cookie======" + cookie + "  host======" + host);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }

   /**
    * 测试头信息
    * @return modelAndView视图模型
    */
   @RequestMapping(value = "testCookie")
   public ModelAndView testCookie(@CookieValue("JSESSIONID") String cookie){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("user", "cookie======" + cookie);
      modelAndView.setViewName("resultMapping");
      return modelAndView;
   }
}
