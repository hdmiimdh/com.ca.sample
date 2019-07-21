package com.ca.sample.dto;

import com.ca.sample.domain.VendingType;

import java.io.Serializable;
import java.util.Objects;

public class VendingItemDto implements Serializable {

    private static final long serialVersionUID = -7676655529477553428L;

    private Long id;
    private VendingType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VendingType getType() {
        return type;
    }

    public void setType(VendingType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VendingItemDto that = (VendingItemDto) o;
        return Objects.equals(id, that.id) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "VendingItemDto{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }


    public static final class VendingItemDtoBuilder {
        private Long id;
        private VendingType type;

        private VendingItemDtoBuilder() {
        }

        public static VendingItemDtoBuilder aVendingItemDto() {
            return new VendingItemDtoBuilder();
        }

        public VendingItemDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public VendingItemDtoBuilder withType(VendingType type) {
            this.type = type;
            return this;
        }

        public VendingItemDto build() {
            VendingItemDto vendingItemDto = new VendingItemDto();
            vendingItemDto.setId(id);
            vendingItemDto.setType(type);
            return vendingItemDto;
        }
    }
}
