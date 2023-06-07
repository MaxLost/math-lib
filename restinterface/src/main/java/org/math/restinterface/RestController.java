package org.math.restinterface;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.math.computations.PlanePoint;
import org.math.computations.functions.AlgebraicInterpolator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @PostMapping("/interpolate/value")
    public RequestResult interpolateGetValue(@RequestBody String requestBody) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode body = mapper.readTree(requestBody);
            Map<String, Object> table = mapper.convertValue(body.get("table"), new TypeReference<Map<String, Object>>(){});

            List<PlanePoint> nodes = new ArrayList<>();
            for (Map.Entry<String, Object> row : table.entrySet()) {
                nodes.add(new PlanePoint(Double.parseDouble(row.getKey()), Double.parseDouble((String) row.getValue())));
            }
            double x = Double.parseDouble(body.get("point").textValue());

            AlgebraicInterpolator interpolator = new AlgebraicInterpolator(nodes);
            double result = interpolator.interpolate(Math.min(table.size() - 1, 8), x);

            return new RequestResult("Success", Double.toString(result));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new RequestResult("Failed", "Request processing failed");
        }
    }

}
