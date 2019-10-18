package diecast.collector.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "dc", name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "year")
    private Integer year;

    public Collection() {}

    public Collection(Integer id) {
        this.id = id;
    }

    public Collection(@NotNull String name, Integer year) {
        this.name = name;
        this.year = year;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
