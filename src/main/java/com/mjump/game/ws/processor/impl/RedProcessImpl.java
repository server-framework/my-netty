package com.mjump.game.ws.processor.impl;

import org.springframework.stereotype.Service;

import com.mjump.game.ws.processor.GameRevProcessor;
import com.mjump.game.ws.util.Result;

@Service(value = "RedProcessImpl")
public class RedProcessImpl implements GameRevProcessor{

	@Override
	public Result online(String userNum, Object param) {
		return null;
	}

	@Override
	public Result ready(String userNum, Object param) {
		return null;
	}

	@Override
	public Result start(String userNum, Object param) {
		return null;
	}

	@Override
	public Result check(String userNum, Object param) {
		return null;
	}

	@Override
	public Result submit(String userNum, Object param) {
		return null;
	}

	@Override
	public Result reject(String userNum, Object param) {
		return null;
	}

	@Override
	public Result abandon(String userNum, Object param) {
		return null;
	}

	@Override
	public Result timeout(String userNum, Object param) {
		return null;
	}

	@Override
	public Result offline(String userNum) {
		return null;
	}

	@Override
	public boolean isValid(String userNum) {
		return false;
	}

}
