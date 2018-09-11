package com.shensha.springbootaophello;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("shensha").password("111").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//开始请求权限配置
                .antMatchers("/", "/goHello").permitAll()//指定了/和/goHello不需要任何认证就可以访问，其他的路径都必须通过身份验证
                .anyRequest().authenticated()//是对http所有的请求必须通过授权认证才可以访问
                .and()//返回一个securityBuilder对象，
                .formLogin().loginPage("/login").permitAll()//授权的方式
                .and().logout().permitAll();
    }
}
