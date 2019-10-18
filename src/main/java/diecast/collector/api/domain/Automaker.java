package diecast.collector.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "dc", name = "automaker")
public class Automaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "country", length = 100)
    private String country;

    public Automaker() {}

    public Automaker(Integer id) {
        this.id = id;
    }

    public Automaker(@NotNull String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

