package io.philoyui.qmier.qmiermanager.controller;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class Index11Controller {

    @RequestMapping("/111")
    public String index() throws IOException {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:\\workplace\\philoyui-stock\\philoyui-stock-manager\\src\\main\\resources\\python\\a.py");
        PyFunction func = interpreter.get("adder",PyFunction.class);
        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("anwser = " + pyobj.toString());
        return "home";
    }

}
