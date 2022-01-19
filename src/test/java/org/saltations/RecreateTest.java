package org.saltations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class RecreateTest
{
    @Test
    public void testWithCommandLineOption() throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST))
        {
            String[] args = new String[] { "-h" };
            PicocliRunner.run(RecreateCmd.class, ctx, args);

            // demo
            assertTrue(baos.toString().contains("Usage: recreate"));
        }
    }
}
