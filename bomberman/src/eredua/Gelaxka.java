package eredua;

public class Gelaxka {
	private String mota;
	
	public Gelaxka(String mota) {
		this.mota = mota;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public boolean bideaDago() {
		return mota.equals("hutsik");
	}

	public void apurtu() {
		if (getMota().equals("biguna")) {
			mota = "hutsik";
			System.out.println("Bloke biguna zegoen eta orain hutsik dago!!");
		} else if (mota.equals("gogorra")) {
	        	System.out.println("Bloke gogorra da, ezin da apurtu!!");
	    	}
	}

	public void suaJarri() {
		this.mota = "sua";
	}

	public void suaKendu() {
		this.mota = "hutsik";
	}

}
