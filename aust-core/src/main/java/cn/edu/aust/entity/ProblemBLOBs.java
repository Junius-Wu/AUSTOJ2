package cn.edu.aust.entity;

import java.util.Date;

public class ProblemBLOBs extends Problem {
    private String description;

    private String input;

    private String output;

    private String sampleInput;

    private String sampleOutput;

    private String hit;

    public ProblemBLOBs(Integer id, String title, String keyword, Byte catelog, Byte stage, Byte spj, Integer timeLimit, Integer memoryLimit, Byte difficulty, Integer accepted, Integer solved, Integer submit, Integer submitUser, Integer authorId, String author, Integer contestId, Date createdate, Date modifydate, String description, String input, String output, String sampleInput, String sampleOutput, String hit) {
        super(id, title, keyword, catelog, stage, spj, timeLimit, memoryLimit, difficulty, accepted, solved, submit, submitUser, authorId, author, contestId, createdate, modifydate);
        this.description = description;
        this.input = input;
        this.output = output;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.hit = hit;
    }

    public ProblemBLOBs() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }
}