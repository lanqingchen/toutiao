package com.nowcoder.controller;

import com.nowcoder.model.News;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.INewsService;
import com.nowcoder.service.IUserService;
import com.nowcoder.service.impl.NewsServiceImpl;
import com.nowcoder.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BlueFish on 2016/7/3.
 */
@Controller
public class HomeController {

    @Autowired
    private INewsService newsServiceImpl;

    @Autowired
    private IUserService userServiceImpl;

    private List<ViewObject> getNews(int userId, int offset, int limit) {
        List<News> newsList = newsServiceImpl.queryNews(userId, offset, limit);
        List<ViewObject> vos = new ArrayList<>();
        for (News news : newsList) {
            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userServiceImpl.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model, @RequestParam(value = "pop", defaultValue = "0") int pop){
        model.addAttribute("vos", getNews(0,0,10));
        model.addAttribute("pop", pop);
        return "home";
    }

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }
}
