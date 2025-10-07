package com.ordenaris.restaurante

import grails.gorm.transactions.Transactional

@Transactional
class PlatilloService {

    def listaPlatillos() {
        try{
            def list =TipoMenu.findAllByStatusNotEqualsAndTipoPrincipalIsNull(2);

            def lista = list.collect{ tipo ->
                def submenu = TipoMenu.findAllByStatusNotEqualsAndTipoPrincipal(2, tipo).collect{ subtipo ->
                    def platillos = Platillo.findAllByStatusNotEqualsAndTipoMenu(2,subtipo).collect{platillo ->
                        return [
                            uuid: platillo.uuid,
                            nombre: platillo.nombre,
                            descripcion: platillo.descripcion,
                            status: platillo.status,
                        ]
                    }
                    return mapTipoMenu(subtipo, platillos)
                }
                return mapTipoMenu( tipo, submenu )
            }
            return [
                resp: [ success: true, tipoMenu: lista ],
                status: 200
            ]
        }catch(e){
            return [
                resp: [ success: false, mensaje: e.getMessage() ],
                status: 500
            ]
        }
    }

    def mapTipoMenu = { tipo, lista ->
        def obj = [
            name: tipo.nombre,
        ]
        if( lista.size() > 0 ) {
            if (lista[0].containsKey("descripcion")) {
                obj.platillos = lista
                return obj
            }
            obj.submenu = lista
        }
        return obj 
    }

    def nuevoPlatillo( nombre, menuTipo, fechaDisponible, costo, descripcion, platillosDisponibles) {
        try{
            def tipoPrincipal
            tipoPrincipal = TipoMenu.findByUuid(menuTipo)
            
            def nuevo = new Platillo([
                nombre:nombre, 
                tipoMenu: tipoPrincipal, 
                fechaDisponible: fechaDisponible, 
                costo:costo, descripcion:descripcion, 
                platillosDisponibles:platillosDisponibles
            ]).save(flush:true, failOnError:true)
            return [
                resp: [ success: true, data: nuevo.uuid ],
                status: 200
            ]
        }catch(e){ 
            return [
                resp: [ success: false, mensaje: e.getMessage() ],
                status: 500
            ]
        }
    }
}
