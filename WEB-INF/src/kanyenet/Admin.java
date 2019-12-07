/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanyenet;



/**
 *
 *]
 */
public class Admin {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private String userName;
	private String passWord;
        
         
	public void setUserName(String user){
		this.userName = user;
	}
	public void setPassWord(String pass){
		this.passWord = pass;
	}
	public String getUserName(){
		return userName;
	}
	public String getPassWord(){
		return passWord;
	}
}
