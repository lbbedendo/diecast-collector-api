package diecast.collector.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "dc", name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    public Brand() {}

    public Brand(Integer id) {
        this.id = id;
    }

    public Brand(@NotNull String name) {
        this.name = name;
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
}
