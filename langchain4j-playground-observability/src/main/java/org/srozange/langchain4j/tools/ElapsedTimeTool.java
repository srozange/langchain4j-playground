package org.srozange.langchain4j.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class ElapsedTimeTool {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Tool(name = "ElapsedTime", value = """
            Compute the elapsed time between a start date and today, expressed in a given unit.
            Returns an integer. If the arguments are invalid, returns an explicit error message
            describing how to fix them so the tool can be called again.
            """)
    public String elapsedTime(
            @P("Start date in the format DD/MM/YYYY, for example 05/07/2026") String startDate,
            @P("Output unit, one of DAY, WEEK, MONTH, YEAR") OutputUnit unit) {

        if (unit == null) {
            return "Invalid argument 'unit': it is required and must be one of DAY, WEEK, MONTH, YEAR.";
        }

        LocalDate start;
        try {
            start = LocalDate.parse(startDate, FORMATTER);
        } catch (DateTimeParseException e) {
            return "Invalid argument 'startDate': '" + startDate
                    + "'. It must be a valid date in the format DD/MM/YYYY, for example 05/07/2026.";
        }

        LocalDate today = LocalDate.now();
        if (start.isAfter(today)) {
            return "Invalid argument 'startDate': '" + startDate
                    + "' is in the future. It must be today or a past date in the format DD/MM/YYYY.";
        }

        long elapsed = switch (unit) {
            case DAY -> ChronoUnit.DAYS.between(start, today);
            case WEEK -> ChronoUnit.WEEKS.between(start, today);
            case MONTH -> ChronoUnit.MONTHS.between(start, today);
            case YEAR -> ChronoUnit.YEARS.between(start, today);
        };

        return Long.toString(elapsed);
    }
}
