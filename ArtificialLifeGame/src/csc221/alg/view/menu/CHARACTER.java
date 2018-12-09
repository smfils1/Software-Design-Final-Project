package csc221.alg.view.menu;

//TODO: May need better character png images
public enum CHARACTER {
    BLUE("/resources/images/character1.gif"),
    GREEN("/resources/images/character4.gif"),
    RED("/resources/images/character3.gif");

    private String urlCharacter;
    CHARACTER(String urlCharacter){
        this.urlCharacter = urlCharacter;
    }

    public String getUrl(){
        return this.urlCharacter;
    }

}
