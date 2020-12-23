package io.qameta.allure;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

public class DefaultListener implements TestLifecycleListener {

    public void beforeTestStop(final TestResult result) {
        Allure.attachment("Before Test Stop", "Hello, World!");
    }

}
