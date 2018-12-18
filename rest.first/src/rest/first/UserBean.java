package rest.first;

public class UserBean {
	
    private String login;
    private String pwd;

    public UserBean(String pseudo, String password) {
		this.login = pseudo;
		this.pwd = password;
	}
    
    public UserBean(){}

	public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
    
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
