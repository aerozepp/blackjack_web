package core;


public class Card {

    private String face;
    private String suite;
    private int value;
    private String path;
    


    public String getFace() {
        return face;
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

    public String getPath() {
    	
    	this.path = "img/cards/" + face + "_of_" + suite + ".png";
    	
        return path;
    }



    public void setFace(String face) {
        this.face = face;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPath(String name) {
        this.path = name;
    }

   
}
