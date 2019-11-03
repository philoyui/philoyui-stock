package cn.com.gome.page.plugins.login;

public class EmptyLoginUserPlugin implements LoginUserPlugin{
    @Override
    public String getLoginUsername() {
        return "admin";
    }
}
