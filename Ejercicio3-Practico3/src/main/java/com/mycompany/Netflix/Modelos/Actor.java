
package com.mycompany.Netflix.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Actor {
    @NonNull private String nombre;
    @NonNull private String nacionalidad;
}
