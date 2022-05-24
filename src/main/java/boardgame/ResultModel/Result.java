package boardgame.ResultModel;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
public class Result {

    private String whitePlayer;
    private String blackPlayer;
    private String winner;
    private int numberOfMovesWhite;
    private int numberOfMovesBlack;

    private  LocalDateTime time;

}
