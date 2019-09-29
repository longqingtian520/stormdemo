package com.criss.wang.storm.wordcount;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class WordCountRichBolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6150067832473190183L;
	
	private OutputCollector collector;
	
	private Map<String, Integer> map = new HashMap<>();

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		String word = input.getString(0);
		int num = input.getInteger(1);
		if(map.containsKey(word)) {
			num = map.get(word).intValue() + num;
			map.put(word, num);
		}else {
			map.put(word, num);
		}
		
		System.out.println(Thread.currentThread().getId() + "  word:" + word + "  num:" + map.get(word));
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
