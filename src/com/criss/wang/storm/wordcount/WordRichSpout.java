package com.criss.wang.storm.wordcount;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class WordRichSpout extends BaseRichSpout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2073138038901575102L;
	private SpoutOutputCollector collector;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		
	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		String str = "this is a strom demo";
		this.collector.emit(new Values(str));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("love"));
		
	}

}
