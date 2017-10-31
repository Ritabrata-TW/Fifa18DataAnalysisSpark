package spark.mostpowerfulteam;

import java.io.Serializable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class CsvDataImporter implements Serializable{
    public JavaRDD<String> importFile(String path) {
        SparkConfig sparkConfig = new SparkConfig();
        JavaSparkContext javaSparkContext = sparkConfig.javaSparkContext;
        JavaRDD<String> datasetWithHeaders = javaSparkContext.textFile(path);
        return eliminateHeadersFromDataset(datasetWithHeaders);
    }

    private JavaRDD<String> eliminateHeadersFromDataset(JavaRDD<String> datasetWithHeaders) {
        String headerRow = datasetWithHeaders.first();
        return datasetWithHeaders.filter(row -> !row.equals(headerRow));
    }
}
