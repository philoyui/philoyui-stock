package io.philoyui.stock.config;

import cn.com.gome.page.plugins.login.LoginUserPlugin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityLoginUserPlugin implements LoginUserPlugin {

    @Override
    public String getLoginUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
