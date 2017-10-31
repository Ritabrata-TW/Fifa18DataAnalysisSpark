package spark.mostpowerfulteam;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Player implements Serializable {
    String id;
    String name;
    Integer overall;
    Integer potential;
}
