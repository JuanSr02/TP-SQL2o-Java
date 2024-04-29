
package com.mycompany.Netflix.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Director {
 @NonNull private String nombre;
 @NonNull private String nacionalidad;
 @NonNull private String fnac;

}
