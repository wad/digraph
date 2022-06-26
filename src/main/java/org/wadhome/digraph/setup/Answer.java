package org.wadhome.digraph.setup;

public class Answer {

    boolean noAnswerExpected;
    boolean noAnswerFound;
    Integer numericResult;

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