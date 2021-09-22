package zju.bs.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import zju.bs.token.JwtUtil;
import zju.bs.domain.ReturnCode1;
import zju.bs.token.MyRequestWrapper;


public class JWTInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       // return true;

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "123");

            MyRequestWrapper myRequestWrapper = new MyRequestWrapper(request);
            String token = myRequestWrapper.getBody();
            //System.out.println(token);
            JSONObject json = JSONObject.parseObject(token);
            //System.out.println(json);
            //JSONObject json = JSONObject.parseObject(token);
            //System.out.println(json);
            //JSONObject json = MyRequestWrapper.getRequestJsonObject(request);
            //System.out.println(json);
            token = json.getString("token");
            //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjI1NjkxOTUsImFjY291bnQiOiJjb3V0aW5obyIsImVtYWlsIjoiMTMwODAyNDVAcXEuY29tIn0.-AunJeU3Wgw3T9FZOIQUvXaTRg7LiQk9a8k5BMPxeQA";
            //System.out.println(token);
            //System.out.println(json.getString("token"));
            //String token = request.getParameter("token");
            //if(token == null) System.out.println("null");
            //else if(token.equals(""))   System.out.println("wu");
            //else    System.out.println(token);
            if (token != null && !token.equals("")) {
                boolean result = JwtUtil.verify(token);
                if (!result) {
                    PrintWriter writer = response.getWriter();
                    writer.print(JSONObject.toJSONString(new ReturnCode1(115, "本次登录已失效，请重新登录")));
                    writer.flush();
                    writer.close();
                    return false;
                } else {
                    //System.out.println(token);
                    return true;
                }
            } else {
                PrintWriter writer = response.getWriter();
                writer.print(JSONObject.toJSONString(new ReturnCode1(115, "您未登录，请先完成登录")));
                writer.flush();
                writer.close();
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
