package mk.ukim.finki.aud7.benford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LineNumbersReader implements NumbersReader{
    @Override
    public List<Integer> read(InputStream inputStream) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .filter(line -> !line.isEmpty())
                    .map(line -> Integer.parseInt(line.trim()))
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return Collections.emptyList();
        }
    }
}
