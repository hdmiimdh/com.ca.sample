package com.ca.sample.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vending_item")
public class VendingItem implements Serializable {

    private static final long serialVersionUID = 3922137665702480328L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private VendingType type;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public VendingType getType() {
        return type;
    }

    public void setType(final VendingType type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VendingItem that = (VendingItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "VendingItem{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public static final class VendingItemBuilder {
        private Long id;
        private VendingType type;

        private VendingItemBuilder() {
        }

        public static VendingItemBuilder aVendingItem() {
            return new VendingItemBuilder();
        }

        public VendingItemBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public VendingItemBuilder withType(final VendingType type) {
            this.type = type;
            return this;
        }

        public VendingItem build() {
            VendingItem vendingItem = new VendingItem();
            vendingItem.setId(id);
            vendingItem.setType(type);
            return vendingItem;
        }
    }
}
