package io.qameta.allure;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultListener implements IInvokedMethodListener {

    private static final Pattern LABEL_MATCHER = Pattern.compile("^(?<name>.+)[:](?<value>.+)$");

    @Override
    public void beforeInvocation(final IInvokedMethod method,
                                 final ITestResult testResult) {
        System.out.println("Before");
        if (!method.isTestMethod()) {
            return;
        }
        if (Objects.isNull(method.getTestMethod().getGroups())) {
            return;
        }
        for (final String group : method.getTestMethod().getGroups()) {
            final Matcher matcher = LABEL_MATCHER.matcher(group);
            if (matcher.matches()) {
                Allure.label(matcher.group("name"), matcher.group("value"));
            }
        }
    }

    @Override
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult) {
        Allure.attachment("Before Test Stop", "Hello, World!");
    }

}
