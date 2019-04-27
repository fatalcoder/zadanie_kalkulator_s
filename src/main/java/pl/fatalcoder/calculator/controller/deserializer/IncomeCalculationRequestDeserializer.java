package pl.fatalcoder.calculator.controller.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;
import pl.fatalcoder.calculator.controller.dto.IncomeCalculationRequest;

import java.io.IOException;

@JsonComponent
public class IncomeCalculationRequestDeserializer extends JsonDeserializer<IncomeCalculationRequest> {
  private static final String FIELD_COUNTRY_CODE = "countryCode";
  private static final String FIELD_DAILY_INCOME_WITH_TAX = "dailyIncomeWithTax";

  @Override
  public IncomeCalculationRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = jsonParser.getCodec()
        .readTree(jsonParser);

    TextNode countryCodeNode = (TextNode) treeNode.get(FIELD_COUNTRY_CODE);
    NumericNode dailyIncomeWithTaxNode = (NumericNode) treeNode.get(FIELD_DAILY_INCOME_WITH_TAX);

    return new IncomeCalculationRequest(
        countryCodeNode.asText(),
        dailyIncomeWithTaxNode.asInt()
    );
  }
}
