
package com.mycompany.tp3arquitecturasw.ej2Area;

import lombok.Data;
import lombok.NonNull;

@Data
public class Area {
    @NonNull private Integer codigo;
    @NonNull private String nombre;
    @NonNull private String director;

}