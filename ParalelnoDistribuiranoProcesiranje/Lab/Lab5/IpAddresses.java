import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IpAdresses {

    public static class IpListMapper
            extends Mapper<LongWritable, Text, Text, Text> {

        private Text word = new Text();
        private Text ipAddress = new Text();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] parts = line.split(" ");
            String month = parts[3].substring(1, 3);
            String file = parts[6];
            ipAddress.set(parts[0]);
        
            word.set(month + " " + file);
            context.write(word, ipAddress);
        }        
    }

    public static class IpListReducer
            extends Reducer<Text, Text, Text, Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Set<String> ipAddressSet = new HashSet<>();
            Set<String> fileSet = new HashSet<>();
        
            for (Text val : values) {
                ipAddressSet.add(val.toString());
            }
        
            for (Text val : values) {
                fileSet.add(val.toString());
            }
        
            List<String> ipAddressList = new ArrayList<>(ipAddressSet);
            List<String> fileList = new ArrayList<>(fileSet);
        
            List<String> commonIPAddresses = new ArrayList<>();
            for (String ipAddress : ipAddressList) {
                int count = 0;
                for (String file : fileList) {
                    if (file.contains(ipAddress)) {
                        count++;
                    }
                }
                if (count == fileList.size()) {
                    commonIPAddresses.add(ipAddress);
                }
            }
        
            Collections.sort(commonIPAddresses);
        
            String ipAddressString = String.join(",", commonIPAddresses);
        
            result.set(key.toString() + "," + ipAddressString);
        
            context.write(null, result);
        }
        
    }

    public static void main(String[] args) throws Exception {        
        JobConf conf = new JobConf(MaxElement.class);
        conf.setJobName("ip_adresses");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(Map.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(IpListMapper.class);
        conf.setOutputFormat(IpListReducer.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}