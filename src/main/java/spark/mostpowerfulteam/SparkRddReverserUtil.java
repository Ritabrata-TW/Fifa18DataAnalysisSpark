package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;
import scala.Serializable;
import scala.Tuple2;

public class SparkRddReverserUtil<T1, T2> implements Serializable {
    JavaPairRDD<T1, T2> reverseJavaPairRdd(
        JavaPairRDD<T2, T1> clubToAggregatedOverallMapping) {
        return clubToAggregatedOverallMapping.mapToPair(tuple2 -> new Tuple2<>(tuple2._2(), tuple2._1()));
    }
}
