package boardgame.ResultModel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class Res implements Comparable<Res>{
    private String name;
    private int winCount;

    


    @Override
    public int compareTo(Res o) {
        return Integer.compare(this.winCount,o.winCount);
    }
}
