package ua.javarush.textquest.dispatcher;

import lombok.extern.log4j.Log4j2;
import ua.javarush.textquest.injector.ApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Log4j2
@WebServlet("/text-quest/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String httpMethod = req.getMethod();
        String uri = req.getRequestURI();
        String url = uri.replace(req.getContextPath(), "").replace(req.getServletPath(), "");

        Map<String, MethodMap> urlToMethodMap = ApplicationContext.URL_TO_METHOD();
        MethodMap methodMap = urlToMethodMap.get(url);
        Method method = methodMap.getMethod();
        Object controller = methodMap.getControllerInstance();
        HttpMethod methodType = methodMap.getMethodType();

        if (httpMethod.equalsIgnoreCase(methodType.name())) {
            try {
                method.invoke(controller, req, resp);
            } catch (InvocationTargetException | IllegalAccessException ex) {
                LOGGER.error("Error while invoking method {} of controller {}", method.getName(), controller.getClass().getName(), ex);
            }
        } else {
            LOGGER.warn("Method {} is not supported for url {}", httpMethod, url);
        }
    }
}
