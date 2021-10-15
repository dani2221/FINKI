package mk.ukim.finki.aud7.benford;

import java.io.InputStream;
import java.util.List;

public interface NumbersReader {
    List<Integer> read(InputStream inputStream);
}
