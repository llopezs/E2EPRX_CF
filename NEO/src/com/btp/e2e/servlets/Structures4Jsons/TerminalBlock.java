package com.btp.e2e.servlets.Structures4Jsons;

public class TerminalBlock {
	private String terminal = "";
	private boolean done = false;
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
