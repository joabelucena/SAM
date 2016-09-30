package br.com.ttrans.samapp.library;


public class IP implements CharSequence {
	
	private String ip;

	public IP(String ip) {
		super();
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	
	@Override
	public String toString() {
		return ip;
	}

	@Override
	public int length() {
		
		return this.ip.length();
	}

	@Override
	public char charAt(int index) {
		return this.ip.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return this.ip.subSequence(start, end);
	}

}
