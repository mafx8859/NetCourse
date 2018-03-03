package service;

public class ChangeToHtml {
    public String change(String s){
    	s=s.replace(" ","&nbsp;").replace("\n","<br>");
    	return s;
    }
}
