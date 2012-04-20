package com.familug.hvn;

import java.io.IOException;

public class Main {
	private static final int GO_FIRST = 0;
	private static final int HIS_PATH = 1;
	private static final int CONF_PATH = 2;
	private static final int CMD_PATH = 3;
	private static final int TEMP_PATH = 4;

	public static void main(String[] args) {
		Ship mShip = new Ship();

		mShip.order = args[GO_FIRST];
		mShip.historyPath = args[HIS_PATH];
		mShip.configPath = args[CONF_PATH];
		mShip.cmdPath = args[CMD_PATH];
		mShip.tempPath = args[TEMP_PATH];

		try {
			mShip.configAllFile();
			mShip.initMyShip();
			mShip.readHistory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mShip);

	}// main

}// MAIN class