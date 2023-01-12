package pl.mz.vadin.music.app.data.domain;

public enum MusicGenre {
    ROCK("Rock"),
    POP("Pop"),
    CLASSICAL("Classical"),
    JAZZ("Jazz"),
    SOUL("Soul"),
    BLUES("Blues"),
    HIP_HOP("Hip-Hop"),
    REGGAE("Reggae"),
    COUNTRY("Country"),
    METAL("Metal"),
    LATIN("Latin"),
    ELECTRONIC("Electronic"),
    PUNK("Punk"),
    FOLK("Folk"),
    ART_POP("Art pop");

    private String name;

    MusicGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
