package eredua;
import java.until.Random;

public class LabirintoClassic extends Labirintoa{
  public LabirintoClassic(){
    super();
  }

   @Override
    protected char gelaxkaSortu(int i, int j) {
        if (i % 2 == 1 && j % 2 == 1) {
            return 'G'; //G: GOGORRA
        } else {
            double probabilitatea = random.nextDouble();
            if (probabilitatea > 0.4) {
                return 'B'; //B: BIGUNA
            } else {
                probabilitatea = random.nextDouble();
                if (probabilitatea > 0.9 && etsaiKont < 6) {
                    etsaiKont ++;
                    return 'E'; //E: ETSAIA
                } else {
                    return 'H'; //H: HUTSIK
                }
            }
        }
    }
}
