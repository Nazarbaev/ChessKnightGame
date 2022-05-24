package boardgame.ResultModel;

import boardgame.util.repository.GsonRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ResultRepositiry extends GsonRepository<Result> {

    public ResultRepositiry() {
        super(Result.class);
    }
    public List<Res> getTop(int n) {
        List<Res> data = new ArrayList<>();
        elements.stream()
                .map(Result::getWinner)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> data.add(new Res(k, v.intValue())));

        return data.stream().sorted(Comparator.reverseOrder()).limit(n).toList();
    }

}