package org.saltations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.micronaut.configuration.picocli.PicocliRunner;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static java.lang.System.out;

@Slf4j
@Command(name = "recreate", description = "...", mixinStandardHelpOptions = true,
        subcommands = {RecreateCmd.ConfigCmd.class, RecreateCmd.GroupCmd.class, RecreateCmd.TemplateCmd.class})
public class RecreateCmd implements Runnable
{
    private static final Preferences prefs = Preferences.userNodeForPackage(RecreateCmd.class);

    enum PreferenceKey
    {
        TEMPLATE_ROOT_DIR("template.store.root.folder", ".");

        PreferenceKey(String prefKey, String prefDefault)
        {
            this.prefKey = prefKey;
            this.prefDefault = prefDefault;
        }

        final String prefKey;
        final String prefDefault;

        public String prefKey()
        {
            return prefKey;
        }

        public String prefDefault()
        {
            return prefDefault;
        }
    }


    public static void main(String[] args) throws Exception
    {
        PicocliRunner.run(RecreateCmd.class, args);
    }

    public void run()
    {
       out.println("Hi!");
    }

    @Command(name = "config", description = "Manage configuration", mixinStandardHelpOptions = true,
            subcommands = {ConfigListCmd.class, ConfigSetCmd.class, ConfigInitCmd.class, ConfigShowCmd.class}
    )
    static class ConfigCmd implements Runnable
    {
        @Override
        public void run()
        {

        }
    }

    @Command(name = "show", description = "Show possible configuration properties", mixinStandardHelpOptions = true)
    static class ConfigShowCmd implements Runnable
    {
        @Override
        public void run()
        {
            Arrays.asList(PreferenceKey.values()).stream().forEach(pref -> out.println(pref.prefKey() + " defaults to: " + pref.prefDefault()));
        }
    }

    @Command(name = "init", description = "Initialize configuration properties", mixinStandardHelpOptions = true)
    static class ConfigInitCmd implements Runnable
    {
        @Override
        public void run()
        {
            prefs.put(PreferenceKey.TEMPLATE_ROOT_DIR.prefKey, PreferenceKey.TEMPLATE_ROOT_DIR.prefDefault());

            log.info("Successfully initialized");
        }
    }

    @Command(name = "list", description = "List configuration properties", mixinStandardHelpOptions = true)
    static class ConfigListCmd implements Runnable
    {
        @Override
        public void run()
        {
            Arrays.asList(PreferenceKey.values()).stream()
                    .forEach(pref -> out.println(pref.prefKey() + "=" + prefs.get(pref.prefKey(),"UNSET") ));
        }
    }

    @Command(name = "set", description = "Set configuration properties", mixinStandardHelpOptions = true)
    static class ConfigSetCmd implements Runnable
    {
        @Option(names = {"-D"})
        Map<String, String> propertyValueByKey;

        @Override
        public void run()
        {
            if (propertyValueByKey.isEmpty())
            {
                return;
            }

            Set<String> potentialPropertyNames = Arrays.asList(PreferenceKey.values()).stream()
                    .map(pref -> pref.prefKey())
                    .collect(Collectors.toSet());

            propertyValueByKey.entrySet().stream().forEach(entry -> {
                if (potentialPropertyNames.contains(entry.getKey()))
                {
                    prefs.put(entry.getKey(), entry.getValue());
                }
                else {
                    log.error("{} is not a valid configuration property", entry.getKey());
                }
            });
        }
    }


    @Command(name = "group", description = "Manage groupings of templates", mixinStandardHelpOptions = true)
    static class GroupCmd implements Runnable
    {
        @Override
        public void run()
        {

        }
    }

    @Command(name = "template", description = "Manage templates", mixinStandardHelpOptions = true)
    static class TemplateCmd implements Runnable
    {
        @Override
        public void run()
        {

        }
    }
}