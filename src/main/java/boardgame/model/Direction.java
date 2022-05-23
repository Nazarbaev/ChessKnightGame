package boardgame.model;
/** Direction interface provides direction of piece,full implementation in {@link KnightDirection} class
 * */
public interface Direction {

     /** Provides  row changed in position
      * @return  row changed in position*/
    int getRowChange();
    /** Provides  column changed in position
     * @return  row changed in position*/
    int getColChange();

}
