package org.math.restinterface;

import org.math.computations.PlanePoint;
import org.math.computations.functions.AlgebraicInterpolator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @PostMapping("/interpolate/value")
    public RequestResult interpolateGetValue(@RequestParam List<PlanePoint> table, @RequestParam Double point) {

        AlgebraicInterpolator interpolator = new AlgebraicInterpolator(table);
        double result = interpolator.interpolate(Math.max(table.size(), 8), point);

        return new RequestResult("Success", Double.toString(result));

    }

}
