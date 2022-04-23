package com.design.pattern.build;

import design.pattern.build.builder.ResourcePoolConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;

public class BuilderTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testBuilder() {
        ResourcePoolConfig resourcePoolConfig = new ResourcePoolConfig.Builder()
                .setName("resource-pool")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                .build();

        assertNotNull(resourcePoolConfig);
    }

    @Test
    public void testIllegalArgumentBuilder() {
        exception.expect(IllegalArgumentException.class);
        ResourcePoolConfig resourcePoolConfig = new ResourcePoolConfig.Builder()
                .setName("")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                .build();

    }
}
