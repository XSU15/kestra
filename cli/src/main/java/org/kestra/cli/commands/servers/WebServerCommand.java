package org.kestra.cli.commands.servers;

import io.micronaut.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.kestra.cli.AbstractCommand;
import org.kestra.core.utils.Await;
import picocli.CommandLine;

import javax.inject.Inject;

@CommandLine.Command(
    name = "webserver",
    description = "start the webserver"
)
@Slf4j
public class WebServerCommand extends AbstractCommand {
    @Inject
    private ApplicationContext applicationContext;

    public WebServerCommand() {
        super(true);
    }

    @Override
    public void run() {
        super.run();

        Await.until(() -> !this.applicationContext.isRunning());
    }
}