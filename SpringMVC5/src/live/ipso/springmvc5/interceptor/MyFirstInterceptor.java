package live.ipso.springmvc5.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器1
 */
public class MyFirstInterceptor implements HandlerInterceptor {

   /**
    * 当处理器方法执行前使用
    * @param request 请求信息
    * @param response 响应
    * @param handler
    * @return true 放行   false 拦截，不放行(就执行不了处理器方法了)
    * @throws Exception
    */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      System.out.println("preHandle当处理器方法执行前使用");
      return true;
   }

   /**
    * 处理器方法执行后调用
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @param handler object
    * @param modelAndView ModelAndView
    * @throws Exception
    */
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      System.out.println("postHandle处理器方法执行后调用");
   }

   /**
    * 请求处理完毕后调用
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @param handler handler
    * @param ex Exception
    * @throws Exception
    */
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      System.out.println("afterCompletion请求处理完毕后调用");
   }
}
