package com.demo.web.controller;


import com.demo.domain.bean.ChannelBean;
import com.demo.web.controller.base.BaseController;
import com.demo.web.shiro.session.SessionConstants;
import com.demo.web.shiro.session.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Channel控制器
 *
 * @Author Wude
 * @Create 2017-07-07 21:13
 */
@Controller
@RequestMapping(value = "channel")
public class ChannelController extends BaseController {

    @RequestMapping(value = "list")
    public String getAllChannel(Model model) {

        List<ChannelBean> channels = new ArrayList<>();
        channels.add(new ChannelBean("001", "Sneakerhead"));
        channels.add(new ChannelBean("002", "Nike"));
        channels.add(new ChannelBean("003", "Adidas"));

        model.addAttribute("channels", channels);
        return "channel";
    }

    @RequestMapping(value = "select/{channelId}")
    public String selectChannel(@PathVariable String channelId) {

        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getSession().getAttribute(SessionConstants.SESSION_USER_ATTR_KEY);
        sessionUser.setChannel(channelId);
        SecurityUtils.getSubject().getSession().setAttribute(SessionConstants.SESSION_USER_ATTR_KEY, sessionUser);
        return "redirect:/index/view";
    }

}
