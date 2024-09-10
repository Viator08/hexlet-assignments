package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String string;

    public ReversedSequence(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new ReversedSequence(new StringBuilder(string.substring(start, end)).reverse()
                .toString());
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }
}
// END
