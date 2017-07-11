package pp2017.team10.shared;

public class Login extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = -8177275538000046658L;

	public String user;
	public String pass;
	public boolean exists;
	
	public Login(String user, String pass, boolean exists){
		this.user = user;
		this.pass = pass;
		this.exists = exists;
	}
	
}
