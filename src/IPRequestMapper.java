import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IPRequestMapper
        extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private final Text ipAddress = new Text();

    @Override
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().trim();

        // Skip header and blank lines
        if (line.isEmpty() || line.startsWith("timestamp")) {
            return;
        }

        String[] fields = line.split(",");

        // IP address is the second column
        if (fields.length >= 2) {
            ipAddress.set(fields[1].trim());
            context.write(ipAddress, one);
        }
    }
}
