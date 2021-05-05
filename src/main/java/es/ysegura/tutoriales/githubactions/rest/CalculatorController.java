package es.ysegura.tutoriales.githubactions.rest;

import es.ysegura.tutoriales.githubactions.domain.services.Calculator;
import es.ysegura.tutoriales.githubactions.domain.vo.Operands;
import es.ysegura.tutoriales.githubactions.domain.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/",
        consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
        produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class CalculatorController {

    private final Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping("add")
    public ResponseEntity<Result> add(@RequestBody Operands operands){
        return ResponseEntity.ok(calculator.add(operands.getA(), operands.getB()));
    }

    @PostMapping("subtract")
    public ResponseEntity<Result> subtract(@RequestBody Operands operands){
        return ResponseEntity.ok(calculator.subtract(operands.getA(), operands.getB()));
    }

    @PostMapping("multiply")
    public ResponseEntity<Result> multiply(@RequestBody Operands operands){
        return ResponseEntity.ok(calculator.multiply(operands.getA(), operands.getB()));
    }

    @PostMapping("divide")
    public ResponseEntity<Result> divide(@RequestBody Operands operands){
        if (0 == operands.getB()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(calculator.divide(operands.getA(), operands.getB()));
    }
}
