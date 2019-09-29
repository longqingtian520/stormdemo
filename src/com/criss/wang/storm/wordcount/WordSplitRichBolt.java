package com.criss.wang.storm.wordcount;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordSplitRichBolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1555625880179619027L;
	
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		String words = input.getString(0);
		String[] wordArr = words.split(" ");
		for(String str : wordArr) {
			collector.emit(new Values(str, 1));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("word", "num"));
	}

}
