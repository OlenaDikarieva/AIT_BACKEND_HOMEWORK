package org.demointernetshop.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demointernetshop.entity.Manufacturer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Manufacturer", description = "Manufacturer data")

public class ManufacturerDto {
    @Schema(description = "Manufacturer identifier", example = "1")
    private Integer id;
    @Schema(description = "Name of the manufacturer", example = "Apple")
    private String name;

    @Schema(description = "Country of the manufacturer", example = "USA")
    private String country;

    public static ManufacturerDto from(Manufacturer manufacturer) {
        return new ManufacturerDto(manufacturer.getId(), manufacturer.getName(),
                manufacturer.getCountry());
    }
}