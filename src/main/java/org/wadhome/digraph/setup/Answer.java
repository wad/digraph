package org.wadhome.digraph.setup;

public class Answer implements Comparable<Answer> {

    boolean noAnswerExpected;
    boolean noAnswerFound;
    Integer numericResult;
    String comment = null;

    static final String MESSAGE_FOR_NO_ANSWER_FOUND = "NO SUCH ROUTE";

    private Answer(
            boolean noAnswerExpected,
            boolean noAnswerFound,
            Integer numericResult) {
        this.noAnswerExpected = noAnswerExpected;
        this.noAnswerFound = noAnswerFound;
        this.numericResult = numericResult;
    }

    public static Answer noAnswerExpected() {
        return new Answer(true, false, null);
    }

    public static Answer notFound() {
        return new Answer(false, true, null);
    }

    public static Answer numeric(int numericAnswer) {
        return new Answer(false, false, numericAnswer);
    }

    public void addToNumericResult(int valueToAdd) {
        if (noAnswerExpected || noAnswerFound) {
            throw new IllegalStateException("Expected to just have an integer here.");
        }
        numericResult += valueToAdd;
    }

    public Integer getNumericResult() {
        return this.numericResult;
    }

    public boolean getIsAnswerExpected() {
        return !noAnswerExpected;
    }

    public boolean getWasAnswerFound() {
        return !noAnswerFound;
    }

    public void updateToNotFound() {
        noAnswerFound = true;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment == null ? "" : comment;
    }

    @Override
    public int compareTo(Answer o) {
        if (o == null) {
            return 1;
        }
        if (this == o) {
            return 0;
        }
        if (noAnswerExpected && o.noAnswerExpected) {
            return 0;
        }
        if (noAnswerFound && o.noAnswerFound) {
            return 0;
        }
        if (numericResult == null && o.numericResult == null) {
            return 0;
        }
        if (numericResult == null) {
            return -1;
        }
        return numericResult.compareTo(o.numericResult);
    }

    @Override
    public String toString() {
        if (noAnswerExpected) {
            return null;
        }
        if (noAnswerFound) {
            return MESSAGE_FOR_NO_ANSWER_FOUND;
        }
        return String.valueOf(numericResult);
    }
}
