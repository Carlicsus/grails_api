package restaurante_carlos

class UrlMappings {

    static mappings = {
        group "/menu", {
            group "/tipo", {
                post "/nuevo"(controller: "menu", action: "nuevoTipo")
                get "/lista"(controller: "menu", action: "listaTipos")
                get "/ver"(controller: "menu", action: "paginarTipos")
                group "/$uuid", {
                    get "/informacion"(controller: "menu", action: "informacionTipo")
                    patch "/editar"(controller: "menu", action: "editarTipo")
                    patch "/activar"(controller: "menu", action: "editarEstatusTipo"){
                        estatus = 1
                    }
                    patch "/desactivar"(controller: "menu", action: "editarEstatusTipo") {
                        estatus = 0
                    }
                    delete "/eliminar"(controller: "menu", action: "editarEstatusTipo") {
                        estatus = 2
                    }
                }
            }
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
