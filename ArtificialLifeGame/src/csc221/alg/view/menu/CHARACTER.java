package csc221.alg.view.menu;

//TODO: May need better character png images
public enum Character {
    C1("/resources/images/character1.gif"),
    C2("/resources/images/character2.gif"),
    C3("/resources/images/character3.gif");

    private String urlCharacter;
    Character(String urlCharacter){
        this.urlCharacter = urlCharacter;
    }

    public String getUrl(){
        return this.urlCharacter;
    }
}
