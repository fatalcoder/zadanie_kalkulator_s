package pl.fatalcoder.calculator.exchange.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;
import pl.fatalcoder.calculator.exchange.dto.ExchangeRates;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@JsonComponent
public class ExchangeRatesDeserialzer extends JsonDeserializer<ExchangeRates> {
  private static final String FIELD_BASE = "base";
  private static final String FIELD_RATES = "rates";

  @Override
  public ExchangeRates deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    try {
      TreeNode treeNode = jsonParser.getCodec()
          .readTree(jsonParser);

      TextNode baseNode = (TextNode) treeNode.get(FIELD_BASE);
      Currency base = Currency.getInstance(baseNode.asText());

      TreeNode ratesNode = treeNode.get(FIELD_RATES);
      Iterator<String> ratesCurrencies = ratesNode.fieldNames();

      Map<Currency, Double> rates = new HashMap<>();
      while (ratesCurrencies.hasNext()) {
        String currencyCode = ratesCurrencies.next();
        Currency currency = Currency.getInstance(currencyCode);

        DoubleNode rateNode = (DoubleNode) ratesNode.get(currencyCode);

        rates.put(currency, rateNode.doubleValue());
      }

      return new ExchangeRates(base, rates);
    } catch (RuntimeException exception) {
      throw new JsonParseException(jsonParser, "Error during deserializing response body", exception);
    }
  }
}
