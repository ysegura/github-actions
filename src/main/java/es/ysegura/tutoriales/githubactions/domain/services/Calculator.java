package es.ysegura.tutoriales.githubactions.domain.services;

import es.ysegura.tutoriales.githubactions.domain.vo.Result;
import org.springframework.stereotype.Service;

@Service
public class Calculator {
    public Result add(int a, int b) {
        return new Result(a + b);
    }

    public Result subtract(int a, int b){
        return new Result(a - b);
    }

    public Result multiply(int a, int b){
        return new Result(a * b);
    }

    public Result divide(int a, int b){
        return new Result(a / b);
    }


}
