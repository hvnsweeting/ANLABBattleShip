package com.familug.hvn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Ship {
	Coordinate heartCoor = null;
	Coordinate startTail = null;
	Coordinate endTail = null;
	ArrayList<Coordinate> firedArl = null;

	String id = null;
	String order = null;
	String historyPath = null;
	String configPath = null;
	String cmdPath = null;
	String tempPath = null;

	BufferedReader bfHistory = null;
	BufferedWriter bfCommandFile = null;
	BufferedReader bfConfig = null;
	BufferedWriter bfTmp = null;

	public void initMyShip() {
		if (order.equalsIgnoreCase("1"))
			this.id = "1";
		else
			this.id = "2";
		System.out.println("Im " + id);

		firedArl = new ArrayList<Coordinate>();
		// add all point around the heart
		firedArl.add(new Coordinate(heartCoor.y - 1, heartCoor.x));
		firedArl.add(new Coordinate(heartCoor.y, heartCoor.x - 1));
		firedArl.add(heartCoor);
		firedArl.add(new Coordinate(heartCoor.y, heartCoor.x + 1));

		firedArl.add(startTail);
		Coordinate middleTail = getMiddleTail(startTail, endTail);
		firedArl.add(middleTail);
		firedArl.add(endTail);

		// for (Coordinate c : firedArl)
		// System.out.println(c);

	}// initMyShip

	private Coordinate getMiddleTail(Coordinate startTail2, Coordinate endTail2) {
		// duoi nam ngang
		if (startTail2.y == endTail2.y)
			return new Coordinate(startTail2.y, (startTail2.x + endTail2.x) / 2);
		else
			// duoi nam doc
			return new Coordinate((startTail2.y + endTail2.y) / 2, startTail2.x);
	}// getMiddleTail

	@Override
	public String toString() {
		return "Ship [order=" + order + ", historyPath=" + historyPath
				+ ", configPath=" + configPath + ", cmdPath=" + cmdPath
				+ ", tempPath=" + tempPath + "]";
	}

	public void readHistory() throws IOException {
		String line = null;
		String[] tokens;
		while ((line = bfHistory.readLine()) != null) {
			tokens = line.split("[\t]");
			// enemy line
			if (!line.startsWith(this.id)) {
				Coordinate c = new Coordinate(tokens[1]);
				if (!firedArl.contains(c))
					firedArl.add(c);
			} else {
				// Mine
				// ban trung
				if (tokens[2].equalsIgnoreCase("OK")) {
					// TODO ban xung quanh
				} else {
					// TODO ban lung tung
					fire(new Coordinate("7 5"));
				}
			}

		}
		// Read his to find out where enemy fire , it is almost not where he is
		// NOTE coor format is Y then X
		// TODO
		// check line with care about my id
		for(Coordinate c: firedArl)
		{
			System.out.println(c);
		}
	}

	public void configAllFile() throws IOException {
		String line = null;
		String[] tokens = null;
		try {
			// read config file
			bfConfig = new BufferedReader(new FileReader(configPath));
			line = bfConfig.readLine();
			tokens = line.split("[ ]");
			heartCoor = new Coordinate(tokens[0], tokens[1]);

			line = bfConfig.readLine();
			tokens = line.split("[ ]");
			startTail = new Coordinate(tokens[0], tokens[1]);
			endTail = new Coordinate(tokens[2], tokens[3]);
			System.out.println(heartCoor + ": start" + startTail + ":end"
					+ endTail);

			// setup hist and command file
			bfCommandFile = new BufferedWriter(new FileWriter(cmdPath));
			bfHistory = new BufferedReader(new FileReader(historyPath));

		} catch (IOException e) {
			e.printStackTrace();
		}
		// finally {
		// if (bfCommandFile != null)
		// bfCommandFile.close();
		// if (bfConfig != null)
		// bfConfig.close();
		// if (bfHistory != null)
		// bfHistory.close();
		// }// finally
	}// configAllFile

	public void fire(Coordinate c) throws IOException {
		// TODO REad history and decide
		if (!firedArl.contains(c))
			firedArl.add(c);
		bfCommandFile.write("S\t" + c + "\n");
		// them toa do da ban vao firedArl
	}// fire

}// Ship

