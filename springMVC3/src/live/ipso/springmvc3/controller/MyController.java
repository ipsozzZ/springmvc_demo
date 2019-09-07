package live.ipso.springmvc3.controller;

import live.ipso.springmvc3.domain.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@SessionAttributes({"u_name", "age"}) // 将model中key为name的属性转存到session中
// @SessionAttributes(types = String.class) // 将model中String类型的属性全部转存到session中
public class MyController {

   @RequestMapping("toRequest")
   public String toRequest(){
      return "/requestPage/request";
   }

   /**
    * ModelAndView方式传值
    * @param data 要传递的数据
    * @return modelAndView对象
    */
   @RequestMapping("testModelAndView")
   public ModelAndView testModelAndView(String data){

      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("data", data);
      modelAndView.setViewName("/resultPage/result");
      return modelAndView;
   }

   /**
    * Model方式传值
    * 这里使用Map map也可以传值，并且也是默认的参数(只是不常用，常用的是Model和ModelAndView)
    * @param model 默认参数，不需要请求方人为显示提供
    * @param data 传递的数据
    * @return 视图路径字符串
    */
   @RequestMapping("testModel")
   public String testModel(Model model, String data){

      /* 传 key-value */
      model.addAttribute("data", data);

      /* 传 Goods对象 */
      Goods myGoods = new Goods();
      myGoods.setName("apple");
      myGoods.setPrice(5000.00);
      model.addAttribute(myGoods);
      System.out.println(model.asMap());

      /* addAllAttributes方法 传map */
      // 将map中的内容复制到Model中，如果由相同的key则覆盖原key值对应的内容
      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("name", "ipso1");
      hashMap.put("age", 22);
      model.addAllAttributes(hashMap);
      System.out.println(model.asMap()); // 直接打印在控制台

      /* addAllAttributes方法 传collection(集合，即list或者set) */
      // 以集合中数据的类型作为key，将所提供的collection中的所有属性复制到这个map中，如果同类型则会存在覆盖现象
      ArrayList<Object> arrayList = new ArrayList<>();
      arrayList.add("ipso222");
      arrayList.add(100);
      arrayList.add("ipso333"); // 覆盖第一个String类型的数据
      model.addAllAttributes(arrayList);
      System.out.println(model.asMap());

      /* model的containsAttribute方法查看是否包含key值 */
      System.out.println(model.containsAttribute("age"));


      return "/resultPage/result";
   }

   /**
    * 通过类上的@SessionAttributes注解存key为u_name的属性到session中
    * @param model model
    * @return 视图路径
    */
   @RequestMapping("testSession")
   public String testSession(Model model){

      /* 将数据存储到request中 */
      model.addAttribute("u_name", "ipso");
      model.addAttribute("age", 20);
      return "/resultPage/result1";
   }

   /**
    * 从session中取key为u_name的属性
    * @param model  model
    * @param u_name 取出来的值赋给u_name
    * @return 视图路径
    */
   @RequestMapping("testSession2")
   public String testSession2(Model model, @SessionAttribute("u_name") String u_name){
      model.addAttribute("u_name", u_name);
      return "/resultPage/result2";
   }

   /**
    * 当使用模型接收参数时，使用的模型会自动存入model模型中
    * @param myGoods Goods模型
    * @param model   model模型，默认自带模型
    * @return 视图路径
    */
   @RequestMapping("testModelAttribute")
   public String testModelAttribute(@ModelAttribute("myGoods") Goods myGoods, Model model){
      System.out.println(myGoods);
      System.out.println(model.asMap());
      return "/resultPage/result3";
   }

   /**
    * 该方法将在所有被@RequestMapping修饰的方法前执行，他的作用是可以用来提前给model做一些赋值
    * @param model Model模型
    */
   @ModelAttribute
   public void testModelAttribute1(Model model){
      model.addAttribute("user", "root");
      model.addAttribute("pass", "root");
   }
}
