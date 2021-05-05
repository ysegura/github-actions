package es.ysegura.tutoriales.githubactions.domain.vo;

public class Result {
    private final int result;

    public Result(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public String toJson() {
        return "{" + result + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Result)
            return result == ((Result) obj).getResult();
        return false;
    }
}
