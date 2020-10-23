package edu.pucmm.Practica3.DTO;

public class Average {
    private String question;
    private Double average;

    public Average() {
    }

    public Average(String question, Double average) {
        this.question = question;
        this.average = average;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "Average{" +
                "questionId=" + question +
                ", average=" + average +
                '}';
    }
}
