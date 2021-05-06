package org.jboss.fuse.tnb.common.deployment;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public interface Deployable extends BeforeAllCallback, AfterAllCallback {
    void deploy();

    void undeploy();

    /**
     * Open all resources needed after the service is deployed - initialize clients and stuff
     */
    void openResources();

    /**
     * Close all resources used after before the service is undeployed
     */
    void closeResources();

    default void beforeAll(ExtensionContext extensionContext) throws Exception {
        deploy();
        openResources();
    }

    default void afterAll(ExtensionContext extensionContext) throws Exception {
        closeResources();
        undeploy();
    }
}
