package spark.playersbycountry;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
class Player implements Serializable {
    String id;
    String name;
    String nationality;
}
