package restaurante_carlos

import com.ordenaris.restaurante.TipoMenu
import com.ordenaris.restaurante.Platillo
import java.util.regex.*
class BootStrap {

    def init = { servletContext ->
        String.metaClass.soloNumeros = {
            def expresion = '^[0-9]*$' 
            def patter = Pattern.compile(expresion)
            def match = patter.matcher(delegate)
            return match.matches()
        }

        if( TipoMenu.count() == 0 ) {
            new TipoMenu([ nombre: "Desayuno" ]).save(flush:true)
            new TipoMenu([ nombre: "Comida" ]).save(flush:true)
            new TipoMenu([ nombre: "Especiales" ]).save(flush:true)
            new TipoMenu([ nombre: "Postres" ]).save(flush:true)
            new TipoMenu([ nombre: "Bebidas" ]).save(flush:true)
        }

        if (Platillo.count() == 0) {

            // DESAYUNO
            def fitness = TipoMenu.findByNombre("Fitness")
            new Platillo(nombre: "Avena con Frutas", costo: 45, descripcion: "Avena natural con plátano y manzana", tipoMenu: fitness).save(flush: true)
            new Platillo(nombre: "Huevos al Vapor", costo: 55, descripcion: "Huevos cocidos sin grasa con espinaca", tipoMenu: fitness).save(flush: true)

            def vegano = TipoMenu.findByNombre("Vegano")
            new Platillo(nombre: "Tofu con Verduras", costo: 60, descripcion: "Tofu salteado con verduras mixtas", tipoMenu: vegano).save(flush: true)
            new Platillo(nombre: "Smoothie Verde", costo: 40, descripcion: "Licuado de espinaca, piña y manzana verde", tipoMenu: vegano).save(flush: true)

            def ligero = TipoMenu.findByNombre("Ligero")
            new Platillo(nombre: "Yogurt con Granola", costo: 35, descripcion: "Yogurt natural con frutas y granola", tipoMenu: ligero).save(flush: true)
            new Platillo(nombre: "Ensalada de Frutas", costo: 30, descripcion: "Mezcla de frutas frescas de temporada", tipoMenu: ligero).save(flush: true)

            // COMIDA
            def guisado = TipoMenu.findByNombre("Guisado")
            new Platillo(nombre: "Pollo en Mole", costo: 85, descripcion: "Tradicional mole poblano con pollo", tipoMenu: guisado).save(flush: true)
            new Platillo(nombre: "Carne Asada", costo: 90, descripcion: "Carne asada con nopales y cebolla", tipoMenu: guisado).save(flush: true)

            def paraLlevar = TipoMenu.findByNombre("Para llevar")
            new Platillo(nombre: "Tacos al Pastor", costo: 40, descripcion: "Orden de 5 tacos al pastor con piña", tipoMenu: paraLlevar).save(flush: true)
            new Platillo(nombre: "Torta de Milanesa", costo: 50, descripcion: "Torta con milanesa, jitomate y aguacate", tipoMenu: paraLlevar).save(flush: true)

            def veganaComida = TipoMenu.findByNombre("Vegana")
            new Platillo(nombre: "Hamburguesa Vegana", costo: 70, descripcion: "Hamburguesa de garbanzo con vegetales", tipoMenu: veganaComida).save(flush: true)
            new Platillo(nombre: "Tacos de Jamaica", costo: 55, descripcion: "Tacos con flor de jamaica al chipotle", tipoMenu: veganaComida).save(flush: true)

            // ESPECIALES
            def muertos = TipoMenu.findByNombre("Dia de muertos")
            new Platillo(nombre: "Pan de Muerto", costo: 25, descripcion: "Pan tradicional con azúcar", tipoMenu: muertos).save(flush: true)
            new Platillo(nombre: "Chocolate Caliente", costo: 30, descripcion: "Bebida caliente con cacao artesanal", tipoMenu: muertos).save(flush: true)

            def patrias = TipoMenu.findByNombre("Fiestas Patrias")
            new Platillo(nombre: "Chiles en Nogada", costo: 120, descripcion: "Platillo típico con nogada y granada", tipoMenu: patrias).save(flush: true)
            new Platillo(nombre: "Pozole Rojo", costo: 95, descripcion: "Pozole tradicional con carne de cerdo", tipoMenu: patrias).save(flush: true)

            def navidad = TipoMenu.findByNombre("Navidad")
            new Platillo(nombre: "Pavo Navideño", costo: 140, descripcion: "Rebanada de pavo con puré de papa", tipoMenu: navidad).save(flush: true)
            new Platillo(nombre: "Ensalada Navideña", costo: 50, descripcion: "Ensalada con manzana, crema y nuez", tipoMenu: navidad).save(flush: true)

            // POSTRES
            def salados = TipoMenu.findByNombre("Salados")
            new Platillo(nombre: "Pay de Queso", costo: 45, descripcion: "Rebanada de pay de queso cremoso", tipoMenu: salados).save(flush: true)
            new Platillo(nombre: "Empanada de Jamón y Queso", costo: 35, descripcion: "Empanada salada recién horneada", tipoMenu: salados).save(flush: true)

            def lacteos = TipoMenu.findByNombre("Lacteos")
            new Platillo(nombre: "Flan Napolitano", costo: 40, descripcion: "Flan casero con caramelo", tipoMenu: lacteos).save(flush: true)
            new Platillo(nombre: "Gelatina de Leche", costo: 30, descripcion: "Gelatina de leche con sabor a vainilla", tipoMenu: lacteos).save(flush: true)

            def dulces = TipoMenu.findByNombre("Dulces")
            new Platillo(nombre: "Pastel de Chocolate", costo: 60, descripcion: "Rebanada de pastel con cobertura", tipoMenu: dulces).save(flush: true)
            new Platillo(nombre: "Helado de Fresa", costo: 25, descripcion: "Helado artesanal sabor fresa", tipoMenu: dulces).save(flush: true)

            // BEBIDAS
            def naturales = TipoMenu.findByNombre("Naturales")
            new Platillo(nombre: "Jugo de Naranja", costo: 25, descripcion: "Jugo natural recién exprimido", tipoMenu: naturales).save(flush: true)
            new Platillo(nombre: "Agua de Piña", costo: 20, descripcion: "Agua natural de piña con poca azúcar", tipoMenu: naturales).save(flush: true)

            def refrescos = TipoMenu.findByNombre("Refrescos")
            new Platillo(nombre: "Refresco de Cola", costo: 25, descripcion: "Refresco embotellado de 355ml", tipoMenu: refrescos).save(flush: true)
            new Platillo(nombre: "Refresco de Naranja", costo: 25, descripcion: "Refresco sabor naranja 355ml", tipoMenu: refrescos).save(flush: true)

            def energizantes = TipoMenu.findByNombre("Energizantes")
            new Platillo(nombre: "Bebida Energética X", costo: 40, descripcion: "Energizante con cafeína y taurina", tipoMenu: energizantes).save(flush: true)
            new Platillo(nombre: "Energizante Natural", costo: 35, descripcion: "Con extracto de guaraná y miel", tipoMenu: energizantes).save(flush: true)

            def malteadas = TipoMenu.findByNombre("Malteadas")
            new Platillo(nombre: "Malteada de Chocolate", costo: 45, descripcion: "Malteada cremosa sabor chocolate", tipoMenu: malteadas).save(flush: true)
            new Platillo(nombre: "Malteada de Vainilla", costo: 45, descripcion: "Malteada clásica sabor vainilla", tipoMenu: malteadas).save(flush: true)
        }

    }
    def destroy = {
    }
}
