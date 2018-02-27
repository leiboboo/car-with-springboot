package org.apache.boboo.springboot.car.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;

@Controller
public class CaptchaController {
	private static final Logger logger = Logger.getLogger(CaptchaController.class);
	
    private int SSO_SESSION_EXPIRE = 10;// 超时时间10分钟
	
	@Autowired  
    private Producer captchaProducer = null;  
	
	@RequestMapping("/doNotNeedSession/validcode.action") 
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
//        HttpSession session = request.getSession();  
//        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
//        logger.info("******************验证码是: " + code + "******************");  
          
		String captchaguid=request.getParameter("captchaguid");
		
        response.setDateHeader("Expires", 0);  
          
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
          
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
          
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
          
        // return a jpeg  
        response.setContentType("image/jpeg");  
          
        // create the text for the image  
        String capText = captchaProducer.createText();  
          
        // store the text in the session  
//        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        //jedisClient.setKey("KAPTCHA_SESSION_KEY:" + captchaguid, capText,SSO_SESSION_EXPIRE);		
          
        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
          
        // write the data out  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  
	
//	4.controller中取得校验码
//	String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
	
}
