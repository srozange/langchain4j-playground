package org.srozange.langchain4j;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.srozange.langchain4j.agents.ExpertRouterService;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@QuarkusMain
public class EntryPoint implements QuarkusApplication {

    @Inject
    ExpertRouterService routerService;

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello to the Observability demo");
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (true) {
                System.out.print("\nYou > ");
                String userInput = scanner.nextLine();
                if ("quit".equalsIgnoreCase(userInput) || "exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                System.out.print("\nAgent > ");
                System.out.println(routerService.route(userInput));
            }
        }
        return 0;
    }
}
