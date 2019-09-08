package live.ipso.springmvc4.controller;

import live.ipso.springmvc4.domain.Pet;
import live.ipso.springmvc4.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

   @RequestMapping("update/{id}")
   public String update(@PathVariable Integer id, Model model){
      System.out.println(id);

      // 所有爱好列表
      ArrayList<Object> hobbyList = new ArrayList<>();
      hobbyList.add("篮球");
      hobbyList.add("足球");
      hobbyList.add("棒球");
      model.addAttribute("allhobby", hobbyList);

      // 宠物列表
      ArrayList<Object> petList = new ArrayList<>();
      Pet pet1 = new Pet();
      pet1.setId(1);
      pet1.setName("dog");

      Pet pet2 = new Pet();
      pet2.setId(2);
      pet2.setName("pig");

      Pet pet3 = new Pet();
      pet3.setId(3);
      pet3.setName("cat");

      petList.add(pet1);
      petList.add(pet2);
      petList.add(pet3);

      model.addAttribute("petList", petList);

      // 用户自己的信息
      User user = new User();
      user.setName("ipso1");
      user.setAge(20);
      user.setGender(1);
      user.setPet(pet2);
      String[] hobby = new String[]{"篮球", "足球"}; // 我的爱好
      user.setHobby(hobby);
      model.addAttribute("user", user);

      return "result";
   }

   /**
    * 打印修改后的数据
    * 其中@Valid，是对提交过来的数据进行验证，验证规则已经在User对象中
    * BindingResult用来接收验证结果
    * @param user 用户对象模型
    * @return 视图
    */
   @RequestMapping("update2")
   public String update2(@Valid User user, BindingResult result, Model model){
      System.out.println(user);

      if (result.getErrorCount() != 0){
         List<FieldError> fieldErrors = result.getFieldErrors(); // 接收并存储验证结果
         for (FieldError fieldError:fieldErrors) { // 遍历打印验证结果，及错误提示信息
            System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
         }

         // 下面回到原来提交数据的界面
         // 这里传数据代码和update一致
         // return "result";
      }

      return "result1";
   }
}
