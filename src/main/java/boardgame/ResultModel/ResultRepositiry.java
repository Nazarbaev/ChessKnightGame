package boardgame.ResultModel;

import boardgame.util.repository.GsonRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



public class ResultRepositiry extends GsonRepository<Result> {

    public ResultRepositiry() {
        super(Result.class);
    }
    public List<Data> getTop(int n) {
        List<Data> data = new ArrayList<>();
        elements.stream()
                .map(Result::getWinner)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> data.add(new Data(k, v.intValue())));

        return data.stream().sorted(Comparator.reverseOrder()).limit(n).toList();
    }

}