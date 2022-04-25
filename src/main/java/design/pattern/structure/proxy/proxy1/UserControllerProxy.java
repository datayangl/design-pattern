package design.pattern.structure.proxy.proxy1;

import design.pattern.structure.proxy.IUserController;
import design.pattern.structure.proxy.MetricsCollector;
import design.pattern.structure.proxy.RequestInfo;
import design.pattern.structure.proxy.impl.UserController;

/**
 * 代理模式1
 *
 * 思路：通过实现相同接口，将需求 和 拓展功能 进行结合
 * 劣势：需要在代理类中将所有方法都实现一遍，代码逻辑冗余严重
 */
public class UserControllerProxy implements IUserController {
    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxy(MetricsCollector metricsCollector, UserController userController) {
        this.metricsCollector = metricsCollector;
        this.userController = userController;
    }

    @Override
    public void login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        userController.login(telephone, password);
        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login,", startTimestamp, responseTime);
        metricsCollector.recordRequest(requestInfo);
    }

    @Override
    public void register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        userController.register(telephone, password);
        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", startTimestamp, responseTime);
        metricsCollector.recordRequest(requestInfo);
    }
}
