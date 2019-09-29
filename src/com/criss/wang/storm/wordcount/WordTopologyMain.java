package com.criss.wang.storm.wordcount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordTopologyMain {
	
	public static void main(String[] args) {
		TopologyBuilder topology = new TopologyBuilder();
		topology.setSpout("WordRichSpout", new WordRichSpout(), 1);
		topology.setBolt("WordSplitRichBolt", new WordSplitRichBolt(), 4).fieldsGrouping("WordRichSpout", new Fields("love"));
		topology.setBolt("WordCountRichBolt", new WordCountRichBolt(), 2).fieldsGrouping("WordSplitRichBolt", new Fields("word"));
		
		Config config = new Config();
//		config.setNumWorkers(2);
		if(args.length > 0) {
			try {
				// 分布式提交
				StormSubmitter.submitTopology(args[0], config, topology.createTopology());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else { // 本地提交
			LocalCluster localCluster = new LocalCluster();
			localCluster.submitTopology("wordtopologymain", config, topology.createTopology());
		}
	}

}
