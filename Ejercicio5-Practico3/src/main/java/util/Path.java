package util;

import lombok.Getter;

public class Path {

    // Los metodos @Getter son necesarios para acceder desde las variables de Templates de Velocity
    // NO USAR ACCESOS DIRECTOS, SINO SIEMPRE A TRAVÉS DE ESTA CLASE
    public static class Web {

        @Getter
        public static final String CARRITO = "/api/pedidosYa/getCarrito";
        @Getter
        public static final String INICIO = "/api/pedidosYa";
        @Getter
        public static final String COMPRARPRODUCTO = "/api/pedidosYa/comprarProducto";
        @Getter
        public static final String AGREGARPRODUCTO = "/api/pedidosYa/agregarProducto";
        @Getter
        public static final String FINALIZAR = "/api/pedidosYa/finalizarPedido";
        @Getter
        public static final String PEDIDOS = "/api/pedidosYa/getPedidos";
        @Getter
        public static final String VACIAR = "/api/pedidosYa/vaciarCarrito";
    }

    public static class Template {

        public final static String LAYOUT = "templates/layout.vtl";
        public final static String PEDIDOSYA = "templates/pedidosYa.vtl";
        public final static String PEDIDOSYACARRITO = "templates/pedidosYaCarrito.vtl";
        public final static String PEDIDOSYAPEDIDO = "templates/pedidosYaPedidos.vtl";

    }

}
