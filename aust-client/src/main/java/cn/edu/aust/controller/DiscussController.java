package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 由于安全性问题,讨论暂时不开
 * @author Niu Li
 * @since 2017/2/26
 */
@Controller
@RequestMapping("/comment")
public class DiscussController {

  @GetMapping(value = "/**",produces = MediaType.TEXT_HTML_VALUE)
  public String toDiscuss(){
    return "temp";
  }
}
