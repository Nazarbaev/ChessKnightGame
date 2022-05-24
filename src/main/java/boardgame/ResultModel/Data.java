package boardgame.ResultModel;

import lombok.Builder;

@lombok.Data
@Builder
public class Data implements Comparable<Data>{
    private String name;
    private int winCount;

    


    @Override
    public int compareTo(Data o) {
        return Integer.compare(this.winCount,o.winCount);
    }
}
