package DU7.cz.cvut.fel.ts1.model;

public class Article {

    private String name;
    private String DOI;
    private String date;

    public Article(String name, String DOI, String date) {
        this.name = name;
        this.DOI = DOI;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDOI() {
        return DOI;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + ", " + DOI + ", " + date;
    }
}
