package com.cjlgb.design.oauth.controller;

import com.cjlgb.design.common.core.bean.Validation;
import com.cjlgb.design.common.security.annotation.PreAuthorize;
import com.cjlgb.design.common.security.context.SecurityContextHolder;
import com.cjlgb.design.common.oauth.entity.OauthUser;
import com.cjlgb.design.oauth.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author WFT
 * @date 2020/5/9
 * description:用户-控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     * @param user com.cjlgb.design.common.oauth.entity.OauthUser
     */
    @PostMapping(value = "/register")
    public void register(@RequestBody @Validated(value = Validation.Add.class) OauthUser user){
        //  校验用户名是否已存在
        OauthUser dbUser = this.userService.selectByUsername(user.getUsername());
        Assert.isNull(dbUser,"用户名已存在");
        //  校验手机号码是否已存在
        dbUser = this.userService.selectByMobile(user.getUserMobile());
        Assert.isNull(dbUser,"手机号码已存在");
        //  随机选一张图片作为头像
        user.setPortrait(PORTRAIT_LIST.get(Calendar.getInstance().get(Calendar.SECOND) % PORTRAIT_LIST.size()));
        this.userService.save(user);
    }

    /**
     * 获取当前用户的信息
     * @return com.cjlgb.design.common.oauth.entity.OauthUser
     */
    @PreAuthorize
    @GetMapping(value = "/getMyInfo")
    @JsonIgnoreProperties(value = "password")
    public OauthUser getMyInfo(){
        //  获取当前用户Id
        Long userId = SecurityContextHolder.getAuthentication().getId();
        return this.userService.getById(userId);
    }









    private final static List<String> PORTRAIT_LIST = new ArrayList<>(Arrays.asList(
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2374899852,761473673&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=346199926,2023737736&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1117830684,3436070629&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1030001008,428763410&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1211870419,287183681&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1898470484,687743959&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3930259119,3122747772&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3190214567,3115602234&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3501292717,1173296159&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1029892493,2739985477&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3364612452,1613922805&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3064380109,2827138081&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=346199926,2023737736&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1029892493,2739985477&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1120698925,1433377698&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1499440368,1773515551&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2987183059,801137433&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2374899852,761473673&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3364612452,1613922805&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2971348996,3395851350&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3222215937,3162619156&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1898470484,687743959&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3930259119,3122747772&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2616048444,2947523336&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3021850876,2199468535&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=793192241,3926771534&fm=11&gp=0.jpg"
    ));

}
