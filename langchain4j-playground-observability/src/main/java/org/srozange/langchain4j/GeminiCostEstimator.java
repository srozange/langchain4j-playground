package org.srozange.langchain4j;

import io.quarkiverse.langchain4j.cost.CostEstimator;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

/**
 * Alimente la métrique {@code gen_ai_client_estimated_cost_total} en fournissant
 * un prix par token. Sans un tel bean, le coût estimé reste à 0.
 *
 * <p>Prix <b>indicatifs</b> pour Gemini 2.5 Flash (USD par million de tokens) —
 * à vérifier/ajuster selon la grille tarifaire Google en vigueur.</p>
 */
@ApplicationScoped
public class GeminiCostEstimator implements CostEstimator {

    // $ par token = ($ par 1M tokens) / 1_000_000
    private static final BigDecimal PER_MILLION = new BigDecimal("1000000");
    private static final BigDecimal INPUT_PRICE_PER_TOKEN = new BigDecimal("0.30").divide(PER_MILLION);
    private static final BigDecimal OUTPUT_PRICE_PER_TOKEN = new BigDecimal("2.50").divide(PER_MILLION);
    private static final String CURRENCY = "USD";

    @Override
    public boolean supports(SupportsContext context) {
        String model = context.model();
        return model != null && model.startsWith("gemini-2.5-flash");
    }

    @Override
    public CostResult estimate(CostContext context) {
        BigDecimal inputCost = INPUT_PRICE_PER_TOKEN.multiply(BigDecimal.valueOf(safe(context.inputTokens())));
        BigDecimal outputCost = OUTPUT_PRICE_PER_TOKEN.multiply(BigDecimal.valueOf(safe(context.outputTokens())));
        return new CostResult(inputCost, outputCost, CURRENCY);
    }

    private static int safe(Integer value) {
        return value == null ? 0 : value;
    }
}
