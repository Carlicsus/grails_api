package com.ordenaris.restaurante


import grails.rest.*
import grails.converters.*

class PlatilloController {
	static responseFormats = ['json', 'xml']
    def PlatilloService
	
    def listaPlatillos() { 
        def respuesta = PlatilloService.listaPlatillos()
        return respond( respuesta.resp, status: respuesta.status )
    }

    def nuevoPlatillo(){
        def data = request.JSON
        def fechaDisponible = null
        def platillosDisponibles = null

        if(!data.nombre){
            return respond([success:false, mensaje: "El nombre es obligatorio"], status: 400)
        }
        if( data.nombre.soloNumeros() ) {
            return respond([success:false, mensaje: "El nombre debe contener letras y no solo numeros"], status: 400)
        }
        if( data.nombre.size() > 80 ) {
            return respond([success:false, mensaje: "El nombre no puede ser tan largo"], status: 400)
        }
        if( !data.menuTipo ) {
            return respond([success:false, mensaje: "El campo menuTipo es obligatorio"], status: 400)
        }
        if( data.menuTipo.size() != 32 ) {
            return respond([success:false, mensaje: "El uuid del tipo menu es invalido"], status: 400)
        }
        if(!data.costo){
            return respond([success:false, mensaje: "El costo es obligatorio"], status: 400)
        }
        if( !data.costo.soloNumeros() ) {
            return respond([success:false, mensaje: "El costo debe contener solo numeros"], status: 400)
        }
        if( !data.descripcion ) {
            return respond([success:false, mensaje: "La descripcion es obligatorio"], status: 400)
        }
        if( data.descripcion.soloNumeros() ) {
            return respond([success:false, mensaje: "La descripcion debe contener letras y no solo numeros"], status: 400)
        }
        if( data.descripcion.size() > 80 ) {
            return respond([success:false, mensaje: "La descripcion no puede ser tan largo"], status: 400)
        }
        if (data.fechaDisponible) {
            try {
                println data.fechaDisponible
                fechaDisponible = new Date(data.fechaDisponible as Long)
            } catch (e) {
                return respond([success:false, mensaje:"Formato de fecha invalido"], status:400)
            }
        }
        if( data.platillosDisponibles){
            if (!data.platillosDisponibles.soloNumeros()){
                return respond([success:false, mensaje: "Los platillos disponibles deben de ser numeros"], status: 400)
            }
            platillosDisponibles = data.platillosDisponibles
        }
        
        def respuesta = PlatilloService.nuevoPlatillo(
            data.nombre, 
            data.menuTipo, 
            fechaDisponible,
            data.costo.toInteger(),
            data.descripcion,
            platillosDisponibles.toInteger(),
        )
        return respond( respuesta.resp, status: respuesta.status )
    }

    def informacionPlatillo(){
        if( params.uuid.size() != 32 ) {
            return respond([success:false, mensaje: "El uuid es invalido"], status: 400)
        }
        def respuesta = PlatilloService.informacionPlatillo(params.uuid)
        return respond( respuesta.resp, status: respuesta.status )
    }

    def editarPlatillo(){
        def data = request.JSON
        def platillo = PlatilloService.informacionPlatillo(params.uuid)
        def fechaDisponible = platillo.resp.data.fechaDisponible
        def platillosDisponibles = platillo.resp.data.platillosDisponibles

        if(!data.nombre){
            return respond([success:false, mensaje: "El nombre es obligatorio"], status: 400)
        }
        if( data.nombre.soloNumeros() ) {
            return respond([success:false, mensaje: "El nombre debe contener letras y no solo numeros"], status: 400)
        }
        if( data.nombre.size() > 80 ) {
            return respond([success:false, mensaje: "El nombre no puede ser tan largo"], status: 400)
        }
        if( !data.menuTipo ) {
            return respond([success:false, mensaje: "El campo menuTipo es obligatorio"], status: 400)
        }
        if( data.menuTipo.size() != 32 ) {
            return respond([success:false, mensaje: "El uuid del tipo menu es invalido"], status: 400)
        }
        if(!data.costo){
            return respond([success:false, mensaje: "El costo es obligatorio"], status: 400)
        }
        if( !data.costo.soloNumeros() ) {
            return respond([success:false, mensaje: "El costo debe contener solo numeros"], status: 400)
        }
        if( !data.descripcion ) {
            return respond([success:false, mensaje: "La descripcion es obligatorio"], status: 400)
        }
        if( data.descripcion.soloNumeros() ) {
            return respond([success:false, mensaje: "La descripcion debe contener letras y no solo numeros"], status: 400)
        }
        if( data.descripcion.size() > 80 ) {
            return respond([success:false, mensaje: "La descripcion no puede ser tan largo"], status: 400)
        }
        if (data.fechaDisponible) {
            try {
                fechaDisponible = new Date(data.fechaDisponible as Long)
            } catch (e) {
                return respond([success:false, mensaje:"Formato de fecha invalido"], status:400)
            }
        }
        if( data.platillosDisponibles){
            if (!data.platillosDisponibles.soloNumeros()){
                return respond([success:false, mensaje: "Los platillos disponibles deben de ser numeros"], status: 400)
            }
            platillosDisponibles = data.platillosDisponibles
        }

        def respuesta = PlatilloService.editarPlatillo(
            data.nombre, 
            data.menuTipo, 
            fechaDisponible,
            data.costo.toInteger(),
            data.descripcion,
            platillosDisponibles.toInteger(),
            params.uuid
        )
        return respond(respuesta.resp, status: respuesta.status)
    }

    def editarEstatusPlatillo(){
        def respuesta = PlatilloService.editarEstatusPlatillo( params.estatus, params.uuid )
        return respond( respuesta.resp, status: respuesta.status )
    }

}
